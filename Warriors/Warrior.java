package DataStructures.PIK3.WarriorsTask;

public class Warrior extends Character implements SpecialPower, ChangableDefense
{
    private byte specialArmor;
    private byte defense = super.getDefense();

    public Warrior(byte attack, byte defence, byte armor)
    {
        super(attack, defence);
        this.specialArmor = armor;
        setType("warrior");
    }

    public byte getArmor()
    {
        return this.specialArmor;
    }

    @Override
    public void setDefense(byte newDefense)
    {
        this.defense += newDefense;
    }

    @Override
    public byte getDefense()
    {
        return this.defense;
    }

    @Override
    public void setType(String type)
    {
        super.setType(type);
    }

    @Override
    public void power(Character opponent)
    {
        if (opponent instanceof Barbarian)
            setDefense((byte) (getDefense() + getArmor()));
    }

    @Override
    public boolean attack(Character opponent)
    {
        power(opponent);
        return super.attack(opponent);
    }
}
