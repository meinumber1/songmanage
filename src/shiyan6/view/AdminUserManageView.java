package shiyan6.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import shiyan6.entity.User;
import shiyan6.service.UserService;
import shiyan6.service.UserServiceImpl;

/**
 * 管理歌曲信息界面
 *
 */
public class AdminUserManageView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelTabel; // 用来保存Jtable的一个面板
	private JTable table; // 表格
	private JPanel panelButton; // 按钮面板
	private JTextField tfSearch; // 查询输入框
	private JButton btnSearch; // 查询按钮
	private JButton btnAdd; // 添加按钮
	private JButton btnEdit; // 修改按钮
	private JButton btnDelete; // 注销按钮
	private JButton btnExit; // 退出按钮

	private userTableModel uModel; // 自定义的tableModel
	private List<User> users; // 用来存储user

	private UserService userService; // UserService

	public AdminUserManageView() {
		userService = new UserServiceImpl();
		init();
		userManage();
	}

	private void init() {
		System.out.println("初始化一次");
		this.setTitle("用户信息管理");
		this.setSize(600, 500);
		this.setIconifiable(true); // 窗体和最小化
		this.setClosable(true); // 窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// this.setLayout(getLayout()); // 获取顶层布局管理器的样式
		this.setLayout(new BorderLayout());

		panelTabel = new JPanel(new BorderLayout()); // 创建面板
		// 给面板设置边框
		panelTabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询信息"));
		users = userService.findAll();
		table = new JTable(); // 创建表格
		refreshTable(users); // 初始化表格，里面的数据都是从数据库中查询出的

		// table 需要放在滚动面板中
		panelTabel.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(panelTabel, BorderLayout.CENTER);

		panelButton = new JPanel(new GridLayout(8, 1, 10, 30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "查询条件"));
		panelButton.add(new JLabel("请输入用户名"));
		tfSearch = new JTextField(8);
		panelButton.add(tfSearch);
		btnSearch = new JButton("查询");
		panelButton.add(btnSearch);
		btnAdd = new JButton("添加");
		panelButton.add(btnAdd);
		btnEdit = new JButton("修改");
		btnEdit.setEnabled(false);
		panelButton.add(btnEdit);
		btnDelete = new JButton("删除");
		btnDelete.setEnabled(false);
		panelButton.add(btnDelete);
		btnExit = new JButton("退出");
		panelButton.add(btnExit);
		panelButton.add(new JLabel());
		this.add(panelButton, BorderLayout.EAST);
		this.setVisible(true);
	}

	private void userManage() {
		// 获取选中的表格中的行
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (table.getSelectedRow() != -1) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			}
		});
		// 删除
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				if (userService.deleteUser(id)) {
					JOptionPane.showInternalMessageDialog(panelTabel, "删除成功");
					users = userService.findAll();
					refreshTable(users);
				} else {
					JOptionPane.showInternalMessageDialog(panelTabel, "删除失败");
				}
			}
		});
		// 查找
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				// users = null;
				String name = tfSearch.getText().trim();
				users = userService.findByName(name);
				// table.removeAll();
				System.out.println("查找到的数据" + users);
				refreshTable(users);
			}

		});
		/*
		 * 增加 这里在一个JFrame中，调用另外一个JFrame，涉及到了线程问题。 即，如果不加任何东西，那么程序会在把btnAdd的事件执行完之后，才会
		 * new AddUserView，所以，我们看到的效果是，添加成功后，关闭了 被调用的那个JFrame，但是，数据根本就没有更新，需要重新查询，才可以
		 * 看到表格更新了。
		 * 1. 使用线程，sleep一下，但是效果不过
		 * 2. 使用notify和wait，但是没有涉及到同步问题，所以，失败
		 * 3. 使用SwingWork方法，没接触过，结果，失败
		 * 4. 在AddUser中使用，虽然解决了多线程，但是添加了耦合
		 * 综合一下，还是使用了方案4，具体版本，可以通过git倒退。
		 */
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				// 使用线程
				new AdminAddUserView(AdminUserManageView.this);
			}
		});
		// 修改
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString().trim();
				String name = table.getValueAt(row, 1).toString().trim();
				String password = table.getValueAt(row, 2).toString().trim();
				User user = new User(id, name, password, 1);
				new AdminEditUserView(user, AdminUserManageView.this);
				System.out.println(id);
			}
		});
		// 退出按钮
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭窗体
				AdminUserManageView.this.dispose();
			}
		});
	}

	/**
	 * 刷新JTable，并显示数据
	 * 
	 * @param users
	 */
	public void refreshTable(List<User> users) {
		if (users == null || users.size() == 0) {
			JOptionPane.showInternalMessageDialog(AdminUserManageView.this, "Sorry, 暂无数据", "消息提示框",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			uModel = new userTableModel(users);
			// 这里一定要使用table中的setModel方法才可，不能使用table的构造方法，因为table已经在init中实例化了
			table.setModel(uModel);
			System.out.println("refresh" + users);
		}
	}

	/**
	 * 这次使用自定义的TableModel来进行表格数据的填充， 表格的表头信息：id，用户名，密码
	 * 
	 */
	private class userTableModel implements TableModel {
		private List<User> userList = null; // 数据

		public userTableModel(List<User> users) {
			this.userList = users;
		}

		/**
		 * JTable 列的数据类型
		 */
		public Class<?> getColumnClass(int arg0) {
			return String.class;
		}

		/**
		 * JTable的列数，3列
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * JTable的列名
		 */
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "用户id";
			} else if (columnIndex == 1) {
				return "用户名";
			} else if (columnIndex == 2) {
				return "密码";
			} else {
				return "出错";
			}
		}

		/**
		 * JTable显示的数据行数
		 */
		public int getRowCount() {
			return userList.size();
		}

		/**
		 * 获取指定行，指定列的值
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			User user = userList.get(rowIndex);
			if (columnIndex == 0) {
				return user.getId();
			} else if (columnIndex == 1) {
				return user.getName();
			} else if (columnIndex == 2) {
				return user.getPassword();
			} else {
				return "出错";
			}
		}

		/**
		 * 设置单元格是否可编辑
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void removeTableModelListener(TableModelListener l) {

		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		}

		public void addTableModelListener(TableModelListener arg0) {

		}
	}
}
