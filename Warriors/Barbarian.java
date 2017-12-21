package DataStructures.PIK3.WarriorsTask;

public class Barbarian extends Character implements SpecialPower, ChangableAttack
{
    private byte bloodlust;
    private byte attack;

    public Barbarian(byte attack, byte defense, byte bloodlust)
    {
        super(attack, defense);
        this.bloodlust = bloodlust;
        setType("barbarian");
    }

    public byte getBloodlust()
    {
        return this.bloodlust;
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
        if (opponent instanceof Archer)
            setAttack((byte) (getAttack() + getBloodlust()));
    }

    @Override
    public boolean attack(Character opponent)
    {
        this.power(opponent);
        return super.attack(opponent);
    }
}
