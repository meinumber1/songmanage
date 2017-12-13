package shiyan6.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import shiyan6.entity.User;
import shiyan6.service.UserService;
import shiyan6.service.UserServiceImpl;

/**
 * 管理歌曲信息界面
 *
 */
public class UserInfoEditView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // 主面板
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	
	private JLabel labPrepass; // 旧密码
	private JLabel labNewpass; // 新密码
	private JLabel labConfirmpass; // 确认新密码
	private JPasswordField pfPrepass; // 旧密码输入框
	private JPasswordField pfNewpass; // 新密码输入框
	private JPasswordField pfConfirmpass; // 确认密码输入
	private JButton btnConcel; // 取消按钮
	private JButton btnEdit; // 修改按钮
	
	private UserService userService;
	private User user;
	public UserInfoEditView(User user) {
		this.user = user;
		userService = new UserServiceImpl();
		init();
		cancel();
		edit();
		System.out.println("UserInfoEditView");
	}

	/**
	 * 初始化各组件
	 */
	private void init() {
		this.setTitle("修改信息");
		this.setSize(new Dimension(500, 500));
		this.setIconifiable(true); // 窗体最小化
		this.setClosable(true); // 可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 退出
		panelMain = new JPanel(new GridLayout(5, 1)); // 5行1列
		panelMain.add(new JPanel()); // 填充
		
		panel1 = new JPanel();
		labPrepass = new JLabel("旧密码：");
		labPrepass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		pfPrepass = new JPasswordField(8);
		panel1.add(labPrepass);
		panel1.add(pfPrepass);
		panelMain.add(panel1);
		
		panel2 = new JPanel();
		labNewpass = new JLabel("新密码：");
		labNewpass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		pfNewpass = new JPasswordField(8);
		panel2.add(labNewpass);
		panel2.add(pfNewpass);
		panelMain.add(panel2);
		
		panel3 = new JPanel();
		labConfirmpass = new JLabel("再次确认：");
		labConfirmpass.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体
		pfConfirmpass = new JPasswordField(8);
		panel3.add(labConfirmpass);
		panel3.add(pfConfirmpass);
		panelMain.add(panel3);
		
		panel4 = new JPanel(null);
		btnConcel = new JButton("取消");
		btnConcel.setBounds(100, 30, 80, 30);
		btnEdit = new JButton("编辑");
		btnEdit.setBounds(300, 30, 80, 30);
		panel4.add(btnConcel);
		panel4.add(btnEdit);
		panelMain.add(panel4);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
	}
	/**
	 * 取消
	 */
	private void cancel() {
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pfPrepass.setText("");
				pfNewpass.setText("");
				pfConfirmpass.setText("");
			}
		});
	}
	/**
	 * 修改密码
	 */
	private void edit() {
		btnEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 新密码
				String newPwd = new String(pfNewpass.getPassword());
				// 再次输入
				String comPwd = new String(pfConfirmpass.getPassword());
				if (!newPwd.equals(comPwd)) {
					JOptionPane.showMessageDialog(panelMain, "两次输入不一致，请重新输入", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println("new"+newPwd);
				System.out.println("com"+comPwd);
				// 重新为user设置密码
				user.setPassword(newPwd);
				// 保存到数据库中
				if (userService.updatePassword(user)) {
					JOptionPane.showMessageDialog(panelMain, "修改成功", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(panelMain, "修改失败", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		pfPrepass.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e)  {
				// 得到输入的旧密码
				String prePwd = new String(pfPrepass.getPassword());
				if (!user.getPassword().equals(prePwd)) {
					System.out.println("用户密码"+user.getPassword());
					System.out.println("旧密码"+prePwd);
					JOptionPane.showMessageDialog(panelMain, "密码不正确，请重新输入", "消息提示框", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
	}
}
