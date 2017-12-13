package shiyan6.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import shiyan6.entity.User;
import shiyan6.service.UserService;
import shiyan6.service.UserServiceImpl;

public class LoginView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_main; // 主面板
	private JPanel panel_left; // 左侧面板
	private JPanel panel_right; // 右侧标签

	private JLabel lb_img; // 显示图片的标签

	private JLabel lb_uname; // 用户标签
	private JLabel lb_upass; // 密码标签
	private JLabel lb_type; // 登录类型标签

	private JTextField tf_uname; // 用户文本框
	private JPasswordField pf_upass; // 密码文本框

	private JButton btn_login; // 登录按钮
	private JButton btn_register; // 注册按钮

	private JComboBox<String> cb_type; // 用户角色下拉列表框

	private UserService userService;
	private User user;

	public LoginView() {
		userService = new UserServiceImpl();
		user = null;
		init();
		login();
		register();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		this.setSize(600, 300); // 设置窗体大小
		this.setLocationRelativeTo(null); // 设置窗体居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮
		this.setTitle("登录"); // 表题
		this.setResizable(false); // 不可改变窗体大小
		// 初始化面板
		panel_main = new JPanel(new GridLayout(1, 2)); // 网格布局
		panel_left = new JPanel(); // 流式布局
//		panel_right = new JPanel(new GridLayout(4, 2, 0, 10)); // 效果不是很好
		panel_right = new JPanel(null); // 使用绝对定位
//		panel_right.setPreferredSize(new Dimension(300, 200));
		// 初始化控件
		lb_img = new JLabel(new ImageIcon(ClassLoader.getSystemResource("shiyan6/img/login.png")));
		lb_uname = new JLabel("用户：", JLabel.CENTER); // 居中显示
		lb_uname.setBounds(20, 20, 80, 26);
		lb_upass = new JLabel("密码：", JLabel.CENTER);
		lb_upass.setBounds(20, 70, 80, 26);
		lb_type = new JLabel("类型", JLabel.CENTER);
		lb_type.setBounds(20, 120, 80, 26);
		tf_uname = new JTextField(8);
		tf_uname.setBounds(80, 20, 180, 30);
		pf_upass = new JPasswordField(8);
		pf_upass.setBounds(80, 70, 180, 30);
		cb_type = new JComboBox<String>(new String[] { "普通用户", "管理员" });
		cb_type.setBounds(80, 120, 100, 30);
		btn_login = new JButton("登录");
		btn_login.setBounds(30, 170, 80, 26);
		btn_register = new JButton("注册");
		btn_register.setBounds(180, 170, 80, 26);
		// 把相应的控件放到面板中去
		panel_left.add(lb_img);

		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_upass);
		panel_right.add(pf_upass);
		panel_right.add(lb_type);
		panel_right.add(cb_type);
		panel_right.add(btn_login);
		panel_right.add(btn_register);

		// 主面板中放左右两个面板
		panel_main.add(panel_left);
		panel_main.add(panel_right);

		// 再把主面板放到窗体中
		this.getContentPane().add(panel_main);
		//this.pack(); // 收缩一下
		this.setVisible(true); // 显示窗体
	}

	/**
	 * 用户登录
	 */
	private void login() {
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取用户名和密码，类别加1
				String uname = tf_uname.getText().trim();
				String upass = new String(pf_upass.getPassword());
				int type = cb_type.getSelectedIndex() + 1;
				if (uname.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "用户名不能为空");
				} else if (upass.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空");
				}
				user = userService.login(uname, upass);
				System.out.println(user);
				System.out.println(user != null);
				if (null != user) {
					if (type != user.getRole()) {
						JOptionPane.showMessageDialog(LoginView.this, "身份类型错误，请重新选择");
					} else {
						if (type == 1) { // 普通用户
							// 传递过去的user用于显示信息
							new UserMainView(user);
							LoginView.this.dispose(); // 关闭登陆框
						} else { // 管理员
							// 传递过去的user用于显示信息
							new AdminMainView(user);
							LoginView.this.dispose(); // 关闭登陆框
						}
					}
				} else {
					JOptionPane.showMessageDialog(LoginView.this, "用户或密码错误");
				}
			}
		});
	}
	/**
	 * 注册
	 */
	private void register() {
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
			}
		});
	}
}
