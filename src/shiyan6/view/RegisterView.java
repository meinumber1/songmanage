package shiyan6.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import shiyan6.entity.User;
import shiyan6.service.UserService;
import shiyan6.service.UserServiceImpl;
import shiyan6.util.Common;

public class RegisterView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // 主面板
	
	private JLabel labName;  // 用户名
	private JLabel labPass; // 密码
	private JLabel labConfirmpass; // 确认新密码
	
	private JTextField tfName;  // 用户名
	private JPasswordField pfPass; // 旧密码输入框
	private JPasswordField pfConfirmpass; // 确认密码输入
	
	private JButton btnConcel; // 取消按钮
	private JButton btnRegsit; // 修改按钮
	
	private UserService userService;
	
	public RegisterView() {
		userService = new UserServiceImpl();
		init();
		cancel();
		register();
	}

	/**
	 * 初始化各组件
	 */
	private void init() {
		this.setTitle("注册信息");
		this.setSize(350, 300);
		this.setLocationRelativeTo(null); // 设置窗体居中显示
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭按钮
//		panelMain = new JPanel(new GridLayout(5, 1)); // 5行1列，效果极差
		panelMain = new JPanel(null); // 使用绝对布局
		labName = new JLabel("名字：");
		labName.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labName.setBounds(40, 20, 80, 26);
		tfName = new JTextField();
		tfName.setBounds(150, 20, 100, 26);
		panelMain.add(labName);
		panelMain.add(tfName);
		
		labPass = new JLabel("密码：");
		labPass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labPass.setBounds(40, 70, 80, 26);
		pfPass = new JPasswordField();
		pfPass.setBounds(150, 70, 100, 26);
		panelMain.add(labPass);
		panelMain.add(pfPass);
		
		labConfirmpass = new JLabel("再次确认：");
		labConfirmpass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labConfirmpass.setBounds(40, 120, 80, 26);
		pfConfirmpass = new JPasswordField();
		pfConfirmpass.setBounds(150, 120, 100, 26);
		panelMain.add(labConfirmpass);
		panelMain.add(pfConfirmpass);
		
		btnConcel = new JButton("取消");
		btnConcel.setBounds(60, 170, 60, 30);
		btnRegsit = new JButton("注册");
		btnRegsit.setBounds(170, 170, 60, 30);
		panelMain.add(btnConcel);
		panelMain.add(btnRegsit);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
	}
	/**
	 * 取消按钮，将文本框的类容给清空
	 */
	private void cancel() {
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfName.setText("");
				pfPass.setText("");
				pfConfirmpass.setText("");
			}
		});
	}
	/**
	 * 用户注册
	 */
	
	private void register() {
		tfName.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String name = tfName.getText().trim();
				// 查看用户名是否存在
				if (userService.checkUser(name)) {
					JOptionPane.showMessageDialog(panelMain, "该用户名已被注册", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		btnRegsit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = tfName.getText().trim();
				String password = new String(pfPass.getPassword());
				String compassword = new String(pfConfirmpass.getPassword());
				if (name.equals("") || name == null
						|| password.equals("") || password == null
						|| compassword.equals("") || compassword == null) {
					JOptionPane.showMessageDialog(panelMain, "填入信息不能有空", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (!password.equals(compassword)) {
					JOptionPane.showMessageDialog(panelMain, "两次密码不一致", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				User user = new User(Common.getUuid(), name, password, 1);
				System.out.println(user);
				// 把用户信息给添加到数据库中
				if (userService.register(user)) {
					JOptionPane.showMessageDialog(panelMain, "注册成功", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					RegisterView.this.dispose();
					new LoginView();
				} else {
					JOptionPane.showMessageDialog(panelMain, "注册失败", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
	}
}
