package DataStructures.PIK3.WarriorsTask;

public class Archer extends Character implements SpecialPower, ChangableAttack
{
    private byte specialFlameArrows;
    private byte attack;

    public Archer(byte attack, byte defense, byte arrows)
    {
        super(attack, defense);
        this.specialFlameArrows = arrows;
        setType("archer");
    }

    public byte getArrows()
    {
        return this.specialFlameArrows;
    }

    @Override
    public void setType(String type)
    {
        super.setType(type);
    }

    @Override
    public void setAttack(byte newAttack)
    {
        this.attack += newAttack;
    }

    @Override
    public byte getAttack()
    {
        return this.attack;
    }

    @Override
    public void power(Character opponent)
    {
        if (opponent instanceof Warrior)
            setAttack((byte) (getAttack() + getArrows()));
    }

    @Override
    public boolean attack(Character opponent)
    {
        this.power(opponent);
        return super.attack(opponent);
    }


}
