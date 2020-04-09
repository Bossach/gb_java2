package lesson_4;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

public class Chat extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextArea textArea1;
    private JTextArea usersArea;

    private User currentUser;
    private Set<User> userList;

    public Chat(User user, Set<User> userList) {

        currentUser = user;
        this.userList = userList;

        setTitle("coolChat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(700, 400, 640, 480);
        setContentPane(panel1);


        //Отправка по Enter
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        //

        //Отправка по кнопке
        sendButton.addActionListener(e -> sendMessage());
        //

        //Выводим список пользователей
        updUserList(this.userList);

        setVisible(true);
    }

    private void sendMessage() {
        String msg = textField1.getText();
        if (!msg.trim().equals("")) {
            textArea1.append(currentUser.getNickname() + ": " + msg + "\n");
        }
        textField1.setText("");
    }


    private void updUserList(Set<User> userSet) {
        usersArea.setText("");
        for (User user : userSet) {
            usersArea.append(user.getNickname() + "\n");
        }
    }
}
