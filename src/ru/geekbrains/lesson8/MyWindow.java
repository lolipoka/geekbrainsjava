package ru.geekbrains.lesson8;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MyWindow extends JFrame {
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private static final Color BACKGROUND_COLOR_DARK = new Color(60, 63, 65);
    private static final Color BACKGROUND_COLOR_DARKEST = new Color(43, 43, 43);
    private static final Color SELECTION_COLOR = new Color(75, 110, 175);
    private static final Color FONT_COLOR = new Color(187, 187, 187);
    private static final String FONT_NAME = "Segoe UI";
    private static final Font MAIN_FONT = new Font(FONT_NAME, Font.PLAIN, 12);

    public MyWindow() {
        setTitle("Swing demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());
        setUI();

        JMenuBar menuBar = getMainMenuBar();
        add(menuBar, BorderLayout.NORTH);

        JSplitPane splitPane = getSplitPane();
        add(splitPane, BorderLayout.CENTER);

        JPanel toolsPanel = getToolsPanel();
        add(toolsPanel, BorderLayout.EAST);

        JPanel statusPanel = getStatusPanel();
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setUI() {
        UIManager.put("PopupMenu.border", new LineBorder(BACKGROUND_COLOR));
        UIManager.put("MenuBar.border", new LineBorder(BACKGROUND_COLOR));
        UIManager.put("SplitPane.border", new LineBorder(BACKGROUND_COLOR));

        UIManager.put("Menu.background", BACKGROUND_COLOR);
        UIManager.put("Menu.foreground", FONT_COLOR);
        UIManager.put("Menu.selectionBackground", SELECTION_COLOR);
        UIManager.put("Menu.font", MAIN_FONT);

        UIManager.put("MenuItem.background", BACKGROUND_COLOR);
        UIManager.put("MenuItem.foreground", FONT_COLOR);
        UIManager.put("MenuItem.selectionBackground", SELECTION_COLOR);
        UIManager.put("MenuItem.font", MAIN_FONT);

        UIManager.put("TextArea.background", BACKGROUND_COLOR_DARKEST);
        UIManager.put("TextArea.foreground", FONT_COLOR);
        UIManager.put("TextArea.font", new Font("JetBrains Mono", Font.PLAIN, 16));

        UIManager.put("ScrollPane.border", new LineBorder(BACKGROUND_COLOR_DARKEST));
        UIManager.put("ScrollPane.background", BACKGROUND_COLOR_DARKEST);
        UIManager.put("ScrollPane.foreground", FONT_COLOR);

        UIManager.put("ScrollBar.background", BACKGROUND_COLOR_DARK);
        UIManager.put("ScrollBar.foreground", FONT_COLOR);

        UIManager.put("Panel.background", BACKGROUND_COLOR_DARK);
        UIManager.put("Panel.foreground", FONT_COLOR);

        UIManager.put("Label.background", BACKGROUND_COLOR_DARK);
        UIManager.put("Label.foreground", FONT_COLOR);
        UIManager.put("Label.font", MAIN_FONT);

        UIManager.put("Tree.background", BACKGROUND_COLOR_DARK);
        UIManager.put("Tree.foreground", FONT_COLOR);
        UIManager.put("Tree.textBackground", BACKGROUND_COLOR_DARK);
        UIManager.put("Tree.textForeground", FONT_COLOR);
        UIManager.put("Tree.selectionBackground", SELECTION_COLOR);
        UIManager.put("Tree.font", MAIN_FONT);
    }

    private JMenuBar getMainMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(BACKGROUND_COLOR_DARK);

        JMenu menuFile = getMenuFile();
        JMenu menuAbout = getMenuAbout();

        menuBar.add(menuFile);
        menuBar.add(menuAbout);

        return menuBar;
    }

    private JMenu getMenuFile() {
        JMenu menuFile = new JMenu("File");
        menuFile.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem fileSaveAs = new JMenuItem("Save as...");
        fileSaveAs.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setBorder(BorderFactory.createEmptyBorder());

        menuFile.add(fileOpen);
        menuFile.add(fileSave);
        menuFile.add(fileSaveAs);
        menuFile.add(fileExit);

        return menuFile;
    }

    private JMenu getMenuAbout() {
        JMenu menuAbout = new JMenu("About");
        menuAbout.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem aboutContacts = new JMenuItem("Contacts");
        aboutContacts.setBorder(BorderFactory.createEmptyBorder());

        JMenuItem aboutVersion = new JMenuItem("Version info");
        aboutVersion.setBorder(BorderFactory.createEmptyBorder());

        menuAbout.add(aboutContacts);
        menuAbout.add(aboutVersion);

        return menuAbout;
    }

    private JSplitPane getSplitPane() {
        JTree tree = getProjectTree();

        JScrollPane treeView = new JScrollPane(tree);
        JTextArea inputArea = new JTextArea();
        inputArea.setCaretColor(FONT_COLOR);
        inputArea.setMargin(new Insets(0, 10, 0, 0));
        inputArea.setLineWrap(true);

        String text = "public class MyWindow extends JFrame {\n\t\n}\n";
        inputArea.setText(text);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, inputArea);
        splitPane.setDividerSize(1);
        return splitPane;
    }

    private JTree getProjectTree() {
        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode("geekbrainsjava");
        DefaultMutableTreeNode srcNode = new DefaultMutableTreeNode("src");
        projectNode.add(srcNode);

        DefaultMutableTreeNode packageTopNode = new DefaultMutableTreeNode("ru.geekbrains");
        srcNode.add(packageTopNode);

        DefaultMutableTreeNode packageLessonNode = new DefaultMutableTreeNode("lesson8");
        packageTopNode.add(packageLessonNode);

        DefaultMutableTreeNode mainClassNode = new DefaultMutableTreeNode("MainClass.java");
        DefaultMutableTreeNode myWindowNode = new DefaultMutableTreeNode("MyWindow.java");
        packageLessonNode.add(mainClassNode);
        packageLessonNode.add(myWindowNode);

        JTree tree = new JTree(projectNode);
        expandAllNodes(tree, 0, tree.getRowCount());

        return tree;
    }

    private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
        // https://stackoverflow.com/questions/15210979/how-do-i-auto-expand-a-jtree-when-setting-a-new-treemodel
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    private JPanel getToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
        JLabel labelAnt = new JLabel("Ant");
        JLabel labelMaven = new JLabel("Maven");
        JLabel labelGradle = new JLabel("Gradle");
        toolsPanel.add(labelAnt);
        toolsPanel.add(labelMaven);
        toolsPanel.add(labelGradle);
        return toolsPanel;
    }

    private JPanel getStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        JLabel statusLabel = new JLabel("Build completed succesfully in 1 s 213 ms (a minute ago)");
        statusPanel.add(statusLabel);

        return statusPanel;
    }
}

