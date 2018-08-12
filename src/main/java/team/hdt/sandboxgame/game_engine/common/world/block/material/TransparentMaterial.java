package team.hdt.sandboxgame.game_engine.common.world.block.material;

public class TransparentMaterial extends BlockMaterials.Material
{
    public TransparentMaterial(MapColor color)
    {
        super(color);
        this.setReplaceable();
    }

    /**
     * Returns true if the block is a considered solid. This is true by default.
     */
    public boolean isSolid()
    {
        return false;
    }

    /**
     * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
     */
    public boolean blocksLight()
    {
        return false;
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement()
    {
        return false;
    }
}