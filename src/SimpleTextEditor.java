import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SimpleTextEditor extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public SimpleTextEditor() {
        setTitle("Простой текстовый редактор");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        fileChooser = new JFileChooser();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem saveAsItem = new JMenuItem("Сохранить как");

        openItem.addActionListener(new OpenAction());
        saveItem.addActionListener(new SaveAction());
        saveAsItem.addActionListener(new SaveAsAction());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private class OpenAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int returnValue = fileChooser.showOpenDialog(SimpleTextEditor.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    textArea.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SimpleTextEditor.this, "Ошибка при открытии файла: " + ex.getMessage());
                }
            }
        }
    }

    private class SaveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // В этом примере просто используем "Сохранить как" для сохранения.
            saveFile(fileChooser.getSelectedFile());
        }
    }

    private class SaveAsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int returnValue = fileChooser.showSaveDialog(SimpleTextEditor.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                saveFile(file);
            }
        }
    }

    private void saveFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(SimpleTextEditor.this, "Ошибка при сохранении файла: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleTextEditor editor = new SimpleTextEditor();
            editor.setVisible(true);
        });
    }
}
