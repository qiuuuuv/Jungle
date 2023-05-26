package view;

import controller.GameController;
import model.Chessboard;
import model.Timer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static model.PlayerColor.BLUE;

public class BeginFrame extends JFrame {
    public ChessGameFrame chessGameFrame;
    public GameController gameController;
    public AIFrame aiFrame;
    public static ArrayList<User> users = new ArrayList<>();
    public User selectedUser;

    static {
        try {
            File file = new File("user.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String s = bufferedReader.readLine();
            StringBuilder name = new StringBuilder(s.charAt(0) + "");
            StringBuilder password = new StringBuilder(s.charAt(15) + "");
            int score = Integer.parseInt(s.charAt(30) + "");
            while (s.endsWith("0")) {
                char[] chars = s.toCharArray();
                char[] nameChar = Arrays.copyOfRange(chars, 1, 15);
                for (char c : nameChar) {
                    if (c != ' ') {
                        name.append(c);
                    }
                }


                char[] passwordChar = Arrays.copyOfRange(chars, 16, 30);
                for (char c : passwordChar) {
                    if (!(c + "").equals(" ")) {
                        password.append(c);
                    }
                }


                char[] scoreChar = Arrays.copyOfRange(chars, 31, 33);
                for (char c : scoreChar) {
                    int b = Integer.parseInt(String.valueOf(c));
                    score = (score * 10 + b);
                }


                User user = new User(name.toString(), password.toString(), score);
                users.add(user);
                s = bufferedReader.readLine();
                if (s == null) {
                    break;
                }
                name = new StringBuilder(s.charAt(0) + "");
                password = new StringBuilder(s.charAt(15) + "");
                score = Integer.parseInt(s.charAt(30) + "");

            }


        } catch (IOException ignored) {
        }

        for (User user : users) {
            System.out.println(user.name);
        }
    }


    private final int WIDTH;
    private final int HEIGHT;

    public BeginFrame() {
        this.aiFrame = new AIFrame();
        setTitle("Jungle");
        this.WIDTH = 400;
        this.HEIGHT = 500;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        chessGameFrame = new ChessGameFrame(1100, 810);
        chessGameFrame.gameController = new GameController(this.chessGameFrame.getChessboardComponent(), new Chessboard());
        this.gameController=chessGameFrame.gameController;
        chessGameFrame.beginFrame = this;
        chessGameFrame.gameController.beginFrame = this;
        aiFrame.beginFrame = this;


        addBeginButton();
        addAIButton();
        addRankButton();
        addUserButton();
        addLogInButton();
        addSignUpButton();

        Image image = new ImageIcon("resource/background/bg.png").getImage();
        image = image.getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel bg = new JLabel(icon);
        bg.setSize(400, 500);
        bg.setLocation(0, 0);
        add(bg);

    }

    private void addUserButton() {
        JButton button = new JButton("User");
        button.addActionListener((e) -> {
            JFrame jFrame = new JFrame("User");
            Container container = jFrame.getContentPane();
            JTextArea jTextArea = new JTextArea();
            JScrollPane jScrollPane = new JScrollPane(jTextArea);
//            StringBuilder s = new StringBuilder();
//            for (User user : users) {
//                s.append(user.toString()).append("\n");
//            }
//            JLabel jLabel = new JLabel(String.valueOf(s));
//            jLabel.setFont(new Font("Rockwell", Font.BOLD, 10));
            container.add(jScrollPane, BorderLayout.CENTER);
            jFrame.setLocation(240, 20);
            jFrame.setSize(600, 300);
            jFrame.setVisible(true);
            jTextArea.setText("");
            for (User user : users) {
                jTextArea.append(user.toString() + "\n");
            }
        });
        button.setLocation(240, 20);
        button.setSize(60, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 10));
        add(button);
    }

    private void addRankButton() {
        JButton button = new JButton("Rank");
        button.addActionListener((e) -> {
            JFrame jFrame = new JFrame("User");
            Container container = jFrame.getContentPane();
            JTextArea jTextArea = new JTextArea();
            JScrollPane jScrollPane = new JScrollPane(jTextArea);
//            StringBuilder s = new StringBuilder();
//            for (User user : users) {
//                s.append(user.toString()).append("\n");
//            }
//            JLabel jLabel = new JLabel(String.valueOf(s));
//            jLabel.setFont(new Font("Rockwell", Font.BOLD, 10));
            container.add(jScrollPane, BorderLayout.CENTER);
            jFrame.setLocation(240, 20);
            jFrame.setSize(600, 300);
            jFrame.setVisible(true);
            ArrayList<User> temp = new ArrayList<>(users);

            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).score < temp.get(j).score) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
            for (User user : temp) {
                jTextArea.append(user.toString() + "\n");
            }


        });
        button.setLocation(310, 20);
        button.setSize(60, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 10));
        add(button);
    }

    private void addLogInButton() {
        JButton button = new JButton("Log in");
        button.addActionListener((e) -> {
            JFrame login = new JFrame("Log in");
            login.setVisible(true);
            login.setTitle("Log in");
            login.setSize(380, 280);
            login.setLocationRelativeTo(null);
            login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            login.setLayout(null);

            JLabel lbAccount = new JLabel("ID");
            lbAccount.setFont(new Font("", Font.BOLD, 20));
            lbAccount.setBounds(55, 40, 50, 40);
            login.add(lbAccount);

            JTextField ID = new JTextField();
            ID.setBounds(110, 45, 200, 30);
            login.add(ID);

            JLabel lbPassword = new JLabel("PW");
            lbPassword.setFont(new Font("", Font.BOLD, 20));
            lbPassword.setBounds(55, 90, 50, 40);
            login.add(lbPassword);

            JTextField PW = new JPasswordField();
            PW.setBounds(110, 95, 200, 30);
            login.add(PW);


            JButton login1 = new JButton("Log in");
            login1.addActionListener((e1) -> {
                String id = ID.getText();
                String pw = PW.getText();
                System.out.println("name:" +users.get(users.size()-1).name);
                for (User user : users) {
                    if (id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please input ID", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    } else if (pw.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please input password", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    if (user.name.equals(id) && user.password.equals(pw)) {  // 包含此用户
                        // 密码正确
                        this.selectedUser = user;
                        login.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Log in Successfully", "Error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        //aiFrame.isLogin  = true;
                        //aiFrame.user = id_user.get(id);
                        //this.setVisible(false);
                    } else if (user.name.equals(id)) {  // 密码错误
                        JOptionPane.showMessageDialog(null, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    } else {  // 没有此用户
                        JOptionPane.showMessageDialog(null, "No such User", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            });
            login1.setBounds(50, 150, 120, 40);
            login1.setFont(new Font("Rockwell", Font.BOLD, 16));
            login.add(login1);

            JButton back = new JButton("Back");
            back.addActionListener((e2) -> {
                login.setVisible(false);
            });
            back.setBounds(190, 150, 120, 40);
            back.setFont(new Font("Rockwell", Font.BOLD, 16));
            login.add(back);

            login.add(login1);
            login.add(back);

            login.setVisible(true);

        });
        button.setLocation(10, 20);
        button.setSize(100, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(button);
    }


    private void addSignUpButton() {
        JButton button = new JButton("Sign up");
        button.addActionListener((e) -> {
            JFrame login = new JFrame("Sign up");
            login.setVisible(true);
            login.setTitle("Sign up");
            login.setSize(380, 280);
            login.setLocationRelativeTo(null);
            login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            login.setLayout(null);

            JLabel lbAccount = new JLabel("ID");
            lbAccount.setFont(new Font("", Font.BOLD, 20));
            lbAccount.setBounds(55, 40, 50, 40);
            login.add(lbAccount);

            JTextField ID = new JTextField();
            ID.setBounds(110, 45, 200, 30);
            login.add(ID);

            JLabel lbPassword = new JLabel("PW");
            lbPassword.setFont(new Font("", Font.BOLD, 20));
            lbPassword.setBounds(55, 90, 50, 40);
            login.add(lbPassword);

            JTextField PW = new JPasswordField();
            PW.setBounds(110, 95, 200, 30);
            login.add(PW);


            JButton login1 = new JButton("Sigh up");
            login1.addActionListener((e1) -> {
                String id = ID.getText();
                String pw = PW.getText();
                User user=new User(id, pw, 100);
                users.add(user);
                this.selectedUser=user;
                System.out.println(users.size());


                File file=new File("user.txt");
                boolean result=file.delete();
                System.out.println(result);
                try {
                    this.gameController.saveUser("user.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



//                try {
//                    FileWriter fileWriter = null;
//                    fileWriter = new FileWriter("user.txt");
//                    for (User user : users) {
//                        fileWriter.write(String.format("%-15s%-15s%d", user.name, user.password, user.score));
//                    }
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }




                JOptionPane.showMessageDialog(null,"Sign up Successfully!");
                login.setVisible(false);
            });
            login1.setBounds(50, 150, 120, 40);
            login1.setFont(new Font("Rockwell", Font.BOLD, 16));
            login.add(login1);

            JButton back = new JButton("Back");
            back.addActionListener((e2) -> {
                login.setVisible(false);
            });
            back.setBounds(190, 150, 120, 40);
            back.setFont(new Font("Rockwell", Font.BOLD, 16));
            login.add(back);

            login.add(login1);
            login.add(back);

            login.setVisible(true);

        });
        button.setLocation(10, 55);
        button.setSize(100, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(button);
    }

    private void addBeginButton() {
        JButton button = new JButton("Begin");
        button.addActionListener((e) -> {
            this.setVisible(false);
            model.Timer.time=45;
            if (GameController.timer == null){
                GameController.timer=new Timer(chessGameFrame.gameController);
                GameController.timer.start();
            }
            chessGameFrame.gameController.reset();
            chessGameFrame.statusLabel.setLocation(770, 81);
            chessGameFrame.repaint();
            chessGameFrame.timeLabel.setVisible(true);
            //chessGameFrame.getChessboardComponent().gameController.reset();
//            chessGameFrame.getChessboardComponent().gameController.AIPlaying = false;
//            chessGameFrame.getChessboardComponent().gameController.AIDiff = Difficulty.NONE;
            chessGameFrame.setVisible(true);
        });
        button.setLocation(100, 100);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addAIButton() {
        JButton button = new JButton("AI");
        button.addActionListener((e) -> {
            this.setVisible(false);
            //chessGameFrame.getChessboardComponent().gameController.AIPlaying = true;
            aiFrame.setVisible(true);
        });
        button.setLocation(100, 300);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}

