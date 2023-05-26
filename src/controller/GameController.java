//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
//IOException 有异常就会停止程序
//Exception 会继续程序
package controller;

import listener.GameListener;
import model.*;
import model.Timer;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static model.PlayerColor.BLUE;
import static model.PlayerColor.RED;

public class GameController implements GameListener {
    public Chessboard model;
    public ChessboardComponent view;
    public BeginFrame beginFrame;
    private PlayerColor currentPlayer;
    private ChessboardPoint selectedPoint;
    public static int turn = 2;
    public boolean isPlayBack;
    public static Timer timer;


    public Chessboard getModel() {
        return model;
    }

    public ChessboardComponent getView() {
        return view;
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessboardPoint getSelectedPoint() {
        return selectedPoint;
    }

    public static int getTurn() {
        return turn;
    }

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = BLUE;
        view.registerController(this);
        this.initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {

            }
        }
    }

    public void swapColor() {
        currentPlayer = currentPlayer == BLUE ? PlayerColor.RED : BLUE;
        turn++;
        if (currentPlayer == BLUE) {
            view.statusLabel.setText("Turn " + turn / 2 + ": BLUE");
        } else {
            view.statusLabel.setText("Turn " + turn / 2 + ": RED");
        }
        Timer.time = 60;

    }


    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (this.selectedPoint != null && this.model.isValidMove(this.selectedPoint, point)) {
            this.model.moveChessPiece(this.selectedPoint, point);
            this.view.setChessComponentAtGrid(point, this.view.removeChessComponentAtGrid(this.selectedPoint));
            this.selectedPoint = null;
            this.swapColor();
            this.view.repaint();
            if (ifEnd()) {
                int reddead = model.getRedDeadSize();
                int bluedead = model.getBlueDeadSize();
                JOptionPane.showMessageDialog(null, currentPlayer == BLUE ? RED : BLUE + " Win.\n" + "Red dead: " + reddead + "\n" + "Blue dead: " + bluedead);
                reset();
            }
//            System.out.println("no capture");
//            if(this.ifEnd(model)){this.winner(model);}
        }
    }

    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (this.selectedPoint == null) {
            if (this.model.getChessPieceOwner(point).equals(this.currentPlayer)) {
                this.selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (this.selectedPoint.equals(point)) {
            this.selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else if (this.model.isValidCapture(this.selectedPoint, point)) {        //this.selectedPoint != null&&
            //&& this.model.isValidMove(this.selectedPoint, point)
            if (this.model.getChessPieceAt(point).getOwner() == BLUE) {
                this.model.blueDead.add(this.model.getChessPieceAt(point));
            }
            if (this.model.getChessPieceAt(point).getOwner() == RED) {
                this.model.redDead.add(this.model.getChessPieceAt(point));
            }
            this.view.removeChessComponentAtGrid(point);
            this.model.removeChess(point);
            this.model.moveChessPiece(this.selectedPoint, point);
            this.view.setChessComponentAtGrid(point, this.view.removeChessComponentAtGrid(this.selectedPoint));
            this.selectedPoint = null;
            this.swapColor();
            this.view.repaint();
        }
        if (ifEnd()) {
            JOptionPane.showMessageDialog(null, currentPlayer == BLUE ? RED : BLUE + " Win");
            reset();
        }
    }

    public void reset() {
        view.getTimeLabel().setText("Time: 60");
        view.getStatusLabel().setText("Turn 1: BLUE");
        model.initGrid();
        model.initPieces();
        view.removeChessComponent();
        view.initiateChessComponent(model);
        currentPlayer = BLUE;
        selectedPoint = null;
        turn = 2;
        model.steps = new ArrayList<>();
        model.blueDead = new ArrayList<>();
        model.redDead = new ArrayList<>();
//        canStepPoints = null;
//        board.initGrid();
//        board.initPieces();
//        view.removeChessComponent();
//        setCanStepFalse();
//        view.statusLabel.setText("Turn 1: BLUE");
//        board.steps = new ArrayList<>();
        view.repaint();
        view.revalidate();
        Timer.time = 60;
//        winner = null;
//
//        timer.time = 45;
    }


    public void saveGame(String fileName) throws IOException {
        String location = fileName + ".txt";
        File file = new File(location);

        if (file.getName().length() == 4) {
            JOptionPane.showConfirmDialog(view, "NULL File!", "", JOptionPane.YES_NO_OPTION);
        } else {
            if (file.exists()) {     // 若文档存在，询问是否覆盖
                int n = JOptionPane.showConfirmDialog(view, "The File Exists. Overwrite it?", "", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.OK_OPTION) {
                    file.delete();
                }

            }
            if (file.createNewFile()) {
                System.out.println("a New File is Created");
            } else {
                System.out.println("No File is Created");
            }
            // 创建文档
            FileWriter fileWriter = new FileWriter(location);

            fileWriter.write("Turn : " + turn / 2 + "\n");
            fileWriter.write("Current Side to Play : " + currentPlayer + "\n\n");

            for (int i = 0; i < model.steps.size(); i++) {
                fileWriter.write(model.steps.get(i).toString());
            }
            fileWriter.write("\n");

            fileWriter.write((currentPlayer == BLUE ? "BLUE" : "RED") + "'s Turn");
            fileWriter.write("\n\n");

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    ChessPiece chessPiece = model.getGrid()[i][j].getPiece();
                    if (model.getGrid()[i][j].getPiece() != null) {
                        if (model.getGrid()[i][j].getPiece().getName().equals("River")) {
                            fileWriter.write(String.format("%-14s", model.getGrid()[i][j].getPiece().getName()));
                        } else {
                            fileWriter.write(String.format("%-4s %-10s", model.getGrid()[i][j].getPiece().getOwner(), model.getGrid()[i][j].getPiece().getName()));

                        }
                    } else {
                        fileWriter.write(String.format("%-14s", ""));
                    }
                }
                fileWriter.write("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }
    }

    public void loadGame() {
        JFrame jFrame = new JFrame("FileChooser");
        Container container = jFrame.getContentPane();
        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(600, 600));
        JPanel jPanel = new JPanel();
        JButton jButton1 = new JButton("New File");
        JButton jButton2 = new JButton("Cancel");
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        JLabel jLabel = new JLabel("", JLabel.CENTER);
        JFileChooser jFileChooser = new JFileChooser("E:\\develop\\代码\\Jungle");
        container.add(jLabel, BorderLayout.NORTH);
        container.add(jScrollPane, BorderLayout.CENTER);
        container.add(jPanel, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setVisible(true);


        jButton1.addActionListener(e -> {
                    File file = null;
                    jFileChooser.setApproveButtonText("OK");
                    jFileChooser.setDialogTitle("Open File");
                    int result = jFileChooser.showOpenDialog(jFrame);
                    jTextArea.setText("");
                    if (result == JFileChooser.APPROVE_OPTION) {
                        file = jFileChooser.getSelectedFile();
                        jLabel.setText("the File you Choose is: " + file.getName());
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        jLabel.setText("NO File is Selected");
                    }

                    FileInputStream fileInputStream = null;
                    if (file != null) {
                        try {
                            fileInputStream = new FileInputStream(file);
                        } catch (FileNotFoundException ex) {
                            jLabel.setText("File NOT Found");
                        }
                    }

                    int readByte;
                    try {
                        if (fileInputStream != null) {
                            while ((readByte = fileInputStream.read()) != -1) {
                                jTextArea.append(String.valueOf((char) readByte));
                            }
                        }
                    } catch (IOException ioException) {
                        jLabel.setText("Read Fail");
                    } finally {
                        try {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        } catch (IOException ignored) {

                        }
                    }
                    if (file != null) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            reset();
                            reader.readLine();
                            reader.readLine();
                            reader.readLine();
                            String line = reader.readLine();
                            ArrayList<Step> steps = new ArrayList<>();
                            while (line.endsWith(")")) {
                                int x1 = line.charAt(8) - '0' - 1;
                                int y1 = line.charAt(12) - '0' - 1;
                                int x2 = line.charAt(19) - '0' - 1;
                                int y2 = line.charAt(23) - '0' - 1;
                                if (model.isValidMove(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2))) {
                                    steps.add(new Step(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2), currentPlayer, false));
                                    model.moveChessPiece(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2));

                                } else {
                                    steps.add(new Step(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2), currentPlayer, true));
                                    model.captureChessPiece(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2));
                                }
                                swapColor();
                                line = reader.readLine();
                            }
                            reset();
                            for (Step step : steps) {
                                int x1 = step.src.getRow();
                                int y1 = step.src.getCol();
                                int x2 = step.dest.getRow();
                                int y2 = step.dest.getCol();
                                if (model.isValidMove(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2))) {
                                    model.moveChessPiece(new ChessboardPoint(x1, y1), new ChessboardPoint(x2, y2));
                                    this.view.setChessComponentAtGrid(step.dest, this.view.removeChessComponentAtGrid(step.src));
                                } else {
                                    model.captureChessPiece(step.src, step.dest);
                                    view.removeChessComponentAtGrid(step.dest);
                                    view.setChessComponentAtGrid(step.dest, view.removeChessComponentAtGrid(step.src));
                                }
                                this.selectedPoint = null;
                                this.swapColor();
                                this.view.repaint();
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }

                }


        );

        jButton2.addActionListener(e -> {
            jFrame.setVisible(false);
        });


    }

    public void regretOneStep() {
        for (int i = 0; i < model.steps.size(); i++) {
            System.out.println(model.steps.get(i).result);
        }
        if (model.steps.size() > 0) {
            model.steps.remove(model.steps.size() - 1);
            ArrayList<Step> temp = new ArrayList<>(model.steps);
            reset();
            for (Step step : temp) {
                if (model.isValidMove(step.src, step.dest)) {
                    model.moveChessPiece(step.src, step.dest);
                    this.view.setChessComponentAtGrid(step.dest, this.view.removeChessComponentAtGrid(step.src));
                } else {
                    model.captureChessPiece(step.src, step.dest);
                    view.removeChessComponentAtGrid(step.dest);
                    view.setChessComponentAtGrid(step.dest, view.removeChessComponentAtGrid(step.src));
                }
                this.selectedPoint = null;
                this.swapColor();
                this.view.repaint();
            }
        }
    }

//    public void playback() {
//        isPlayBack = true;
//        thread = new Thread(() -> {
//            ArrayList<Step> steps = model.steps;
//            reset();
//            for (Step step : steps) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (model.isValidMove(step.src, step.dest)) {
//                    model.moveChessPiece(step.src, step.dest);
//                    this.view.setChessComponentAtGrid(step.dest, this.view.removeChessComponentAtGrid(step.src));
//                } else {
//                    model.captureChessPiece(step.src, step.dest);
//                    view.removeChessComponentAtGrid(step.dest);
//                    view.setChessComponentAtGrid(step.dest, view.removeChessComponentAtGrid(step.src));
//                }
//                this.selectedPoint = null;
//                this.swapColor();
//                this.view.repaint();
//
//            }
//        });
//        thread.start();
//        //this.view.repaint();
//        isPlayBack = false;
//    }

    public void playback() {
        isPlayBack = true;
        Thread thread = new Thread(() -> {
            ArrayList<Step> steps = model.steps;
            reset();
            for (Step step : steps) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (model.isValidMove(step.src, step.dest)) {
                    model.moveChessPiece(step.src, step.dest);
                    this.view.setChessComponentAtGrid(step.dest, this.view.removeChessComponentAtGrid(step.src));
                } else {
                    model.captureChessPiece(step.src, step.dest);
                    view.removeChessComponentAtGrid(step.dest);
                    view.setChessComponentAtGrid(step.dest, view.removeChessComponentAtGrid(step.src));
                }
                this.selectedPoint = null;
                this.swapColor();
                this.view.repaint();

            }
        });
        thread.start();
        //this.view.repaint();
        isPlayBack = false;
    }

    public boolean ifEnd() {
        boolean boo = model.getBlueDeadSize() == 8 || model.getRedDeadSize() == 8;
        return (model.getGrid()[0][3].getPiece() != null || model.getGrid()[8][3].getPiece() != null) || boo;
    }

    public void saveUser(String fileName) throws IOException {
        // 创建文档
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(String.format("%-15s%-15s%-15s\n", "name", "password", "score"));
        for (int i = 0; i < BeginFrame.users.size(); i++) {
            fileWriter.write(String.format("%-15s%-15s%d\n", BeginFrame.users.get(i).name, BeginFrame.users.get(i).password, BeginFrame.users.get(i).score));
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
