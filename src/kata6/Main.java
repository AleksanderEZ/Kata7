package kata6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata6.control.BlockPresenter;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.view.BlockDisplay;

public class Main extends JFrame{
    private Block block;
    private BlockDisplay blockDisplay;
    private Map<String, Command> commands;
    
    public Main() {
        block = new Block(4, 4);
        setTitle("Block shifter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(917, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().add(blockPanel(), BorderLayout.CENTER);
        BlockPresenter blockPresenter = new BlockPresenter(blockDisplay, block);
        add(buttonBar(), BorderLayout.SOUTH);
        commands = addCommand();
    }
    
    private void execute() {
        setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        blockDisplay = panel;
        return panel;
    }
    
    private Map<String, Command> addCommand(){
        Map<String, Command> map = new HashMap<>();
        map.put("U", new UpCommand(block));
        map.put("D", new DownCommand(block));
        map.put("L", new LeftCommand(block));
        map.put("R", new RightCommand(block));
        return map;
    }

    private JMenuBar buttonBar() {
        JMenuBar bar = new JMenuBar();
        bar.setLayout(new FlowLayout(FlowLayout.CENTER));
        bar.add(button("U"));
        bar.add(button("D"));
        bar.add(button("L"));
        bar.add(button("R"));
        return bar;
    }

    private JButton button(String r) {
        JButton button = new JButton(r);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(r).execute();
            }
        });
        return button;
    }
    
    public static void main(String[] args) {
        new Main().execute();
    }

}
