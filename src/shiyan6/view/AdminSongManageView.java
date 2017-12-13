package shiyan6.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import shiyan6.entity.Song;
import shiyan6.service.SongService;
import shiyan6.service.SongServiceImpl;
import shiyan6.util.Common;

/**
 * 管理员--管理歌曲信息界面
 *
 */
public class AdminSongManageView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // 主面板

	private JPanel panelTop; // 主面板的上部
	private JPanel panelSearch; // 搜索面板
	private JLabel labKey; // 关键字
	private JTextField tfSearch; // 搜索框
	private JButton btnSearch; // 查找按钮

	private JPanel panelSearchType; // 按什么查找
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton[] btnSearchTypes = new JRadioButton[4]; // 条件，如歌名，语言，类别，歌手
	private JLabel labSearch;

	private JScrollPane panelTable; // 表格面板
	private myTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象

	private JPanel panelBottom; // 底部面板，包括输入框，添加、删除、修改
	private JLabel labName;
	private JLabel labLanguage;
	private JLabel labCategory;
	private JLabel labSinger;
	private JTextField tfName; // 输入歌曲名字
	private JTextField tfLanguage; // 输入语言
	private JTextField tfCategory; // 输入类别
	private JTextField tfSinger; // 歌手
	private JButton btnAdd; // 添加按钮
	private JButton btnEdit; // 修改按钮
	private JButton btnDelete; // 删除按钮

	private SongService songService;

	public AdminSongManageView() {
		songService = new SongServiceImpl();
		init();
		getTableSelectedRow();
		addTableRow();
		editTableRow();
		deleteTableRow();
		getSearchTypeValue();
		displayMessage();
		find();
		System.out.println("songmanagerView");
	}

	/**
	 * 初始化各组件
	 */
	private void init() {
		this.setTitle("歌曲管理");
		this.setSize(new Dimension(750, 550));
		this.setIconifiable(true); // 窗体最小化
		this.setClosable(true); // 可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 退出
		panelMain = new JPanel(new BorderLayout());

		panelTop = new JPanel(new GridLayout(2, 1, 0, 0));
		panelTop.setLocation(0, 20);
		panelSearch = new JPanel();
		panelSearch.setPreferredSize(new Dimension(750, 30));
		labKey = new JLabel("关键字");
		tfSearch = new JTextField("请输入歌名",20);
		tfSearch.setPreferredSize(new Dimension(100, 20));
		btnSearch = new JButton("查找");
		btnSearch.setPreferredSize(new Dimension(60, 20));
		panelSearch.add(new JLabel());
		panelSearch.add(labKey);
		panelSearch.add(tfSearch);
		panelSearch.add(new JLabel());
		panelSearch.add(btnSearch);
		panelSearch.add(new JLabel());
		panelTop.add(panelSearch);

		panelSearchType = new JPanel();
		panelSearchType.setPreferredSize(new Dimension(750, 30));
		labSearch = new JLabel("查询");
		panelSearchType.add(labSearch);
		btnSearchTypes[0] = new JRadioButton("歌名", true);
		btnSearchTypes[1] = new JRadioButton("语言");
		btnSearchTypes[2] = new JRadioButton("类别");
		btnSearchTypes[3] = new JRadioButton("歌手");

		// System.out.println("名字"+btnSearchTypes[1].getText());
		for (JRadioButton jRadioButton : btnSearchTypes) {
			buttonGroup.add(jRadioButton);
			panelSearchType.add(jRadioButton);
		}
		panelTop.add(panelSearchType);
		panelMain.add(panelTop, BorderLayout.NORTH);

		String[] columnNames = { "id", "歌名", "语言", "类别", "歌手" };
		// 从数据库中查询所有数据
		List<Song> songs = songService.findAll();
		System.out.println(songs);
		String[][] tableValues = new String[songs.size()][5];
		for (int i = 0; i < songs.size(); i++) {
			tableValues[i][0] = songs.get(i).getId();
			tableValues[i][1] = songs.get(i).getName();
			tableValues[i][2] = songs.get(i).getLanguage();
			tableValues[i][3] = songs.get(i).getCategory();
			tableValues[i][4] = songs.get(i).getSinger();
			// System.out.println(tableValues[i][0]);
		}
		// String[][] tableValues = {{"1", "1", "1", "1", "1"},
		// {"2", "2", "2", "2", "2"},
		// {"3", "3", "3", "3", "3"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"}
		// };
		tableModel = new myTableModel(tableValues, columnNames);
		tableModel.isCellEditable(0, 0);
		table = new JTable(tableModel);
//		table.setRowSorter(new TableRowSorter<>(tableModel)); // 不可排序
		table.setRowHeight(20); // 设置行高
		table.getTableHeader().setBackground(Color.GRAY); // 表头颜色
		table.setLocation(20, 80);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 只能选中单行
//		table.setEnabled(false); // 设置表格为不可编辑
		panelTable = new JScrollPane(table);
		panelTable.setBorder(BorderFactory.createTitledBorder("歌曲信息"));
		panelMain.add(panelTable, BorderLayout.CENTER);

		panelBottom = new JPanel();
		labName = new JLabel("歌名");
		tfName = new JTextField(8);
		labLanguage = new JLabel("语言");
		tfLanguage = new JTextField(8);
		labCategory = new JLabel("类别");
		tfCategory = new JTextField(8);
		labSinger = new JLabel("歌手");
		tfSinger = new JTextField(8);
		panelBottom.add(labName);
		panelBottom.add(tfName);
		panelBottom.add(labLanguage);
		panelBottom.add(tfLanguage);
		panelBottom.add(labCategory);
		panelBottom.add(tfCategory);
		panelBottom.add(labSinger);
		panelBottom.add(tfSinger);
		btnAdd = new JButton("增加");
		btnEdit = new JButton("修改");
		btnDelete = new JButton("删除");
		panelBottom.add(btnAdd);
		panelBottom.add(btnEdit);
		panelBottom.add(btnDelete);

		panelMain.add(panelBottom, BorderLayout.SOUTH);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
		// System.out.println("1234");
	}

	/**
	 * 获取选中的表格的一行
	 */
	private void getTableSelectedRow() {
		table.addMouseListener(new MouseAdapter() {
			// 发生了点击事件
			public void mouseClicked(MouseEvent e) {
				// 获得被选中行的索引
				int selectedRow = table.getSelectedRow();
				// 从表格模型中获得指定单元格的值
				// Object id = tableModel.getValueAt(selectedRow, 0);
				Object name = tableModel.getValueAt(selectedRow, 1);
				Object language = tableModel.getValueAt(selectedRow, 2);
				Object category = tableModel.getValueAt(selectedRow, 3);
				Object singer = tableModel.getValueAt(selectedRow, 4);

				tfName.setText(name.toString());
				tfLanguage.setText(language.toString());
				tfCategory.setText(category.toString());
				tfSinger.setText(singer.toString());
			}
		});
	}

	/**
	 * 添加一行
	 */
	private void addTableRow() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String[] rowValues = {"add",tfName.getText(),tfLanguage.getText(),
				// tfCategory.getText(),tfSinger.getText()};
				String name =  tfName.getText().trim();
				String language =  tfLanguage.getText().trim();
				String category =  tfCategory.getText();
				String singer = tfSinger.getText();
				if (name.equals("") || name == null 
						|| language.equals("") || language == null
						|| category.equals("") || category == null
						|| singer.equals("") || singer == null) {
					JOptionPane.showMessageDialog(panelTable, "不能为空", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Song song = new Song(Common.getUuid(),name,language, category, singer);
				String[] rowValues = new String[5];
				if (songService.addSong(song)) {
					JOptionPane.showMessageDialog(panelMain, "添加成功", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					rowValues[0] = song.getId();
					rowValues[1] = song.getName();
					rowValues[2] = song.getLanguage();
					rowValues[3] = song.getCategory();
					rowValues[4] = song.getSinger();
					// 将输入框清空
					tfName.setText("");
					tfCategory.setText("");
					tfSearch.setText("");
					tfLanguage.setText("");
					// 将新增的行加入表格中
					tableModel.addRow(rowValues);
				} else {
					JOptionPane.showMessageDialog(panelTable, "添加失败", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
				}
				// int preCount = table.getRowCount();
				// System.out.println(preCount);
				// tableModel.addRow(rowValues);
				// int rowCount = table.getRowCount();
				// System.out.println(rowCount);
				// try {
				// if (rowCount > preCount) {
				// // String id = tableModel.getValueAt(rowCount, 0).toString();
				// String name = tableModel.getValueAt(rowCount, 1).toString();
				// String language = tableModel.getValueAt(rowCount, 2).toString();
				// String category = tableModel.getValueAt(rowCount, 3).toString();
				// String singer = tableModel.getValueAt(rowCount, 4).toString();
				//
				// tfName.setText(name);
				// tfLanguage.setText(language);
				// tfCategory.setText(category);
				// tfSinger.setText(singer);
				// }
				// } catch (Exception e2) {
				// // TODO: handle exception
				// }
			}
		});
	}

	/**
	 * 修改信息
	 */
	private void editTableRow() {
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow(); // 获取被选中行的索引
				Song song = new Song();
				if (selectedRow != -1) { // 判断是否存在被选中行
					// System.out.println(tableModel.getValueAt(selectedRow, 0));
					song.setId(tableModel.getValueAt(selectedRow, 0).toString());
					song.setName(tfName.getText());
					song.setLanguage(tfLanguage.getText());
					song.setCategory(tfCategory.getText());
					song.setSinger(tfSinger.getText());
					if (songService.updateSong(song)) {
						song = songService.findById(song.getId());
						JOptionPane.showMessageDialog(panelMain, "修改成功", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
						tableModel.setValueAt(song.getId(), selectedRow, 0);
						tableModel.setValueAt(song.getName(), selectedRow, 1);
						tableModel.setValueAt(song.getLanguage(), selectedRow, 2);
						tableModel.setValueAt(song.getCategory(), selectedRow, 3);
						tableModel.setValueAt(song.getSinger(), selectedRow, 4);
					} else {
						JOptionPane.showMessageDialog(panelTable, "修改失败", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
		});
	}

	/**
	 * 删除一条信息
	 */
	private void deleteTableRow() {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow(); // 获取被选中行的索引
				if (selectedRow != -1) {
					String id = tableModel.getValueAt(selectedRow, 0).toString();
					if (songService.deletSong(id)) {
						JOptionPane.showMessageDialog(panelMain, "删除成功", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
						// 将输入框清空
						tfName.setText("");
						tfCategory.setText("");
						tfSearch.setText("");
						tfLanguage.setText("");
						tableModel.removeRow(selectedRow);
						
					} else {
						JOptionPane.showMessageDialog(panelTable, "删除失败", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
	}

	/**
	 * 获取勾选框中的值，将该值的信息给放入到tfsearch中，用作提示信息
	 */
	private void getSearchTypeValue() {
		for (JRadioButton jRadioButton : btnSearchTypes) {
			jRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jRadioButton.setSelected(true); // 将该jRadioButton设置为选中状态
					String type = jRadioButton.getText();
					tfSearch.setText("请输入" + type);
					// System.out.println(type);
				}
			});
		}
	}

	/**
	 * 查询
	 */
	private void find() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Song> songs = null;
				String type = "歌名";
				for (int i = 0; i < btnSearchTypes.length; i++) {
					if (btnSearchTypes[i].isSelected()) {
						type = btnSearchTypes[i].getText();
					}
				}
				if ("歌名".equals(type)) {
					String name = tfSearch.getText();
					System.out.println("name:" + name);
					songs = songService.findByName(name);
					// System.out.println("查询出来的"+songs);
				} else if ("歌手".equals(type)) {
					String singer = tfSearch.getText();
					songs = songService.findBySinger(singer);
					// System.out.println("查询出来的"+songs);
				} else if ("语言".equals(type)) {
					String language = tfSearch.getText();
					songs = songService.findBylanguage(language);
					// System.out.println("查询出来的"+songs);
				} else if ("类别".equals(type)) {
					String category = tfSearch.getText();
					songs = songService.findByCategory(category);
					// System.out.println("查询出来的"+songs);
				} else {
					JOptionPane.showMessageDialog(panelMain, "请选择查询类型", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
				}
				fillSonginfoToTable(songs);
			}
		});
	}
	// 查询时使用，清空表格数据，再重新添加行
	private void fillSonginfoToTable(List<Song> songs) {
		if (songs.isEmpty()) {
			JOptionPane.showMessageDialog(panelMain, "Sorry, 未查询到数据，请从新输入", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
		}
		tableModel.setRowCount(0); // 将表格清空
		String[][] tableValues = new String[songs.size()][5];
		// 重新装填数据
		for (int j = 0; j < songs.size(); j++) {
			tableValues[j][0] = songs.get(j).getId();
			tableValues[j][1] = songs.get(j).getName();
			tableValues[j][2] = songs.get(j).getLanguage();
			tableValues[j][3] = songs.get(j).getCategory();
			tableValues[j][4] = songs.get(j).getSinger();
			// System.out.println(tableValues[j][0]);
			tableModel.addRow(tableValues[j]);
		}
	}

	/**
	 * 处理输入框的提示消息处理，当光标进入search输入框时，将输入框清空
	 */
	private void displayMessage() {
		tfSearch.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				tfSearch.setText("");
			}
		});
	}
	/**
	 * myTableModel，继承DefaultTableModel，
	 * 只重写了isCellEditable，让其不可编辑 
	 *
	 */
	private class myTableModel extends DefaultTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public myTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			// TODO Auto-generated constructor stub
		}

		// 让表格不可编辑，重写了DefaultTableModel中的方法
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}
}
