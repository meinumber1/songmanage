package shiyan6.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

/**
 * 修改用户信息窗体
 *
 */
public class AdminEditUserView extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // 主面板

	private JLabel labName; // 用户名
	private JLabel labPass; // 密码
	private JLabel labConfirmpass; // 确认新密码

	private JTextField tfName; // 用户名
	private JPasswordField pfPass; // 旧密码输入框
	private JPasswordField pfConfirmpass; // 确认密码输入

	private JButton btnConcel; // 取消按钮
	private JButton btnEdit; // 修改按钮
	private AdminUserManageView adminUserManageView;
	private UserService userService;
	private User user;
	/**
	 * 构造器，传入user的目的是为了将用户的名字给显示到输入框中，当然也可以只传入用户的名字。
	 * adminUserManageView，为了显示效果，同时避免多线程，才传入的，当然增加了代码的耦合。
	 * @param user
	 * @param adminUserManageView
	 */
	public AdminEditUserView(User user, AdminUserManageView adminUserManageView) {
		userService = new UserServiceImpl();
		this.user = user;
		this.adminUserManageView = adminUserManageView;
		init(); // 初始化组件
		cancel(); // 取消
		editUser(); // 修改
	}

	/**
	 * 初始化各组件
	 */
	private void init() {
		this.setTitle("修改用户"); // 窗体标题
		this.setSize(380, 300); // 窗体大小
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭按钮
		this.setLocationRelativeTo(null); // 设置窗体居中显示
		// panelMain = new JPanel(new GridLayout(5, 1)); // 5行1列，效果极差
		panelMain = new JPanel(null); // 使用绝对布局
		labName = new JLabel("名字：");
		labName.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labName.setBounds(40, 20, 80, 26);
		tfName = new JTextField();
		tfName.setText(user.getName());
		tfName.setBounds(150, 20, 80, 26);
		panelMain.add(labName);
		panelMain.add(tfName);

		labPass = new JLabel("密码：");
		labPass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labPass.setBounds(40, 70, 80, 26);
		pfPass = new JPasswordField();
		pfPass.setBounds(150, 70, 80, 26);
		panelMain.add(labPass);
		panelMain.add(pfPass);

		labConfirmpass = new JLabel("再次确认：");
		labConfirmpass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		labConfirmpass.setBounds(40, 120, 80, 26);
		pfConfirmpass = new JPasswordField();
		pfConfirmpass.setBounds(150, 120, 80, 26);
		panelMain.add(labConfirmpass);
		panelMain.add(pfConfirmpass);

		btnConcel = new JButton("取消");
		btnConcel.setBounds(60, 170, 60, 30);
		btnEdit = new JButton("修改");
		btnEdit.setBounds(170, 170, 60, 30);
		panelMain.add(btnConcel);
		panelMain.add(btnEdit);

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
	 * 修改用户
	 */
	private void editUser() {
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 获取输入框中的值，包括用户名、密码和确认密码
				String name = tfName.getText().trim();
				String password = new String(pfPass.getPassword());
				String compassword = new String(pfConfirmpass.getPassword());
				// 如果其中有控值，则提示。当然放在输入框的FocusListener中的LostFocus中更好
				if (name.equals("") || name == null || password.equals("") || password == null || compassword.equals("")
						|| compassword == null) {
					JOptionPane.showMessageDialog(panelMain, "填入信息不能有空", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 判断两次密码是否一致
				if (!password.equals(compassword)) {
					JOptionPane.showMessageDialog(panelMain, "两次密码不一致", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 重新为user赋值
				user = new User(user.getId(), name, compassword, 1);
				System.out.println("修改后：" + user);
				// 把用户信息给添加到数据库中
				if (userService.updateUserInfo(user)) {
					JOptionPane.showMessageDialog(panelMain, "修改成功", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					adminUserManageView.refreshTable(userService.findAll());
					AdminEditUserView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(panelMain, "修改失败", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
	}
}
