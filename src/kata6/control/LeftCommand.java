package kata6.control;

import kata6.model.Block;

public class LeftCommand implements Command{

    private final Block block;

    public LeftCommand(Block block) {
        this.block = block;
    }
    
    @Override
    public String name() {
        return "left";
    }

    @Override
    public void execute() {
        block.left();
    }

}
