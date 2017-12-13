package shiyan6.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shiyan6.entity.User;

/**
 * 管理园的主窗体
 *
 */
public class AdminMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMain;  // 主面板
	private JPanel panelTop;  // 主面板的上部
	private JLabel labWelcome; // 上部的欢迎信息

	private JPanel panelLeft; // 主面板的左边
	private JButton btnSongManage; // 歌曲管理按钮
	private JButton btnUserManage; // 用户管理按钮
	private JButton btnEditUserInfo; // 修改密码按钮
	
	private JDesktopPane panelContent; // 用来显示类容
	private JLabel labImg; // 用来存放图片
	private User user;
	public AdminMainView(User user) {
		this.user = user;
		init();
		songManageView();
		adminUserManageView();
		userInfoEditView();
	}
	/**
	 * 初始化组件信息
	 */
	private void init() {
		panelMain = new JPanel(new BorderLayout());
		panelTop = new JPanel();
		labWelcome = new JLabel("欢     迎    管    理    员      "+user.getName()+"  进    入    歌    曲    管    理    系    统");
		labWelcome.setFont(new Font("宋体", Font.BOLD, 22));// 设置字体
		labWelcome.setForeground(Color.BLUE); // 设置颜色
		panelTop.add(labWelcome);
		// panelTop.setSize(1000, 200); // 设置大小
		panelTop.setPreferredSize(new Dimension(1000, 100));
		// EventQueue事件，让labWelcome给动起来
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Thread(new DynaminThread()).start();
			}
		});
		panelMain.add(panelTop, BorderLayout.NORTH); // 添加到主面板的上方
		
		panelLeft = new JPanel(new GridLayout(9, 1, 0, 40));
		panelLeft.setBorder(BorderFactory.createTitledBorder("菜单栏"));
		btnSongManage = new JButton("歌曲管理");
		btnUserManage = new JButton("用户管理");
		btnEditUserInfo = new JButton("修改密码");
		panelLeft.add(new JLabel());  //填充
		panelLeft.add(btnSongManage);
		panelLeft.add(new JLabel());
		panelLeft.add(btnUserManage);
		panelLeft.add(new JLabel());
		panelLeft.add(btnEditUserInfo);
		// panelLeft.setSize(400, 600); // 设置大小，此方法无效
		panelLeft.setPreferredSize(new Dimension(200, 600));
		panelMain.add(panelLeft, BorderLayout.WEST); // 添加到主面板的左边
		
		panelContent = new JDesktopPane();
		ImageIcon image = new ImageIcon("src/shiyan6/img/song.jpg");
//		System.out.println(image);
		labImg = new JLabel(image);
		labImg.setBounds(15, 15, 750, 550); // 设置位置和大小
		panelContent.setPreferredSize(new Dimension(800, 600));
		panelContent.add(labImg, new Integer(Integer.MIN_VALUE)); // 将存放图片的label放在最下层
		panelContent.setBorder(BorderFactory.createTitledBorder("内容"));
		panelMain.add(panelContent, BorderLayout.CENTER); // 添加到主界面的中间
		
		this.setTitle("歌曲管理系统"); // 窗体标题
		this.getContentPane().add(panelMain); // 将主面板给加入到窗体中
		this.setSize(1000, 800); // 设置窗体大小
		this.setLocationRelativeTo(null); // 让窗体显示在屏幕中央
		this.setResizable(false); // 窗体大小不可变
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置关闭按钮
		this.setBackground(Color.white);
		this.setVisible(true);  // 让窗体可见
	}
	/**
	 * 让欢迎的label动起来，
	 * 因为swing是单线程的，因此需要启动一个线程
	 *
	 */
	private class DynaminThread implements Runnable {
		public void run() {
			while(true) {
				for(int i = 1000; i > -980; i --) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					labWelcome.setLocation(i, 30);
				}
			}
		}
	}
	/**
	 * 添加事件，跳转管理歌曲的页面
	 */
	private void songManageView() {
		btnSongManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				AdminSongManageView sManagerView = new AdminSongManageView();
				// 将指定的视图给添加到JDeskTopPanel中
				panelContent.add(sManagerView);
				// 将视图放在最前面
				sManagerView.toFront();
			}
		});
	}
	/**
	 * 用户管理界面
	 */
	private void adminUserManageView() {
		btnUserManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserManageView adminUserManageView = new AdminUserManageView();
				// 将指定的视图给添加到JDeskTopPanel中
				panelContent.add(adminUserManageView);
				// 将视图放在最前面
				adminUserManageView.toFront();
			}
		});
	}
	/**
	 * 进入密码修改界面
	 */
	private void userInfoEditView() {
		btnEditUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoEditView userInfoEditView = new UserInfoEditView(user);
				// 将指定的视图给添加到JDeskTopPanel中
				panelContent.add(userInfoEditView);
				// 将视图给放在最前面
				userInfoEditView.toFront();
			}
		});
	}
}
