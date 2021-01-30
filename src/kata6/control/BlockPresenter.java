package kata6.control;


import kata6.model.Block;
import kata6.view.BlockDisplay;

public class BlockPresenter implements Block.Observer{
    private final BlockDisplay blockDisplay;
    private final Block block;

    public BlockPresenter(BlockDisplay blockDisplay, Block block) {
        this.blockDisplay = blockDisplay;
        this.block = block;
        this.block.register(this);
        this.blockDisplay.register(moved());
        this.paint();
    }

    @Override
    public void updated() {
        paint();
    }

    private void paint() {
        blockDisplay.show(block.getX(), Block.MAX - block.getY() -1);
    }

    private BlockDisplay.Moved moved() {
        return new BlockDisplay.Moved() {
            @Override
            public void to(int x, int y) {
                block.pos(x, Block.MAX - y - 1);
            }
        };
    }
}
