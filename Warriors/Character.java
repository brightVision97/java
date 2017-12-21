package DataStructures.PIK3.WarriorsTask;

public abstract class Character
{
    private byte attack;
    private byte defense;
    private byte health;
    private String type;

    public Character(byte attack, byte defense)
    {
        this.attack = attack;
        this.defense = defense;
        this.health = Byte.MAX_VALUE;
        this.type = null;
    }

    public byte getAttack()
    {
        return this.attack;
    }

    public byte getDefense()
    {
        return this.defense;
    }

    public byte getHealth()
    {
        return this.health;
    }

    public void setHealth(byte newHealth)
    {
        this.health = newHealth;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public boolean attack(Character opponent)
    {
        while (true)
        {
            if (this.getHealth() <= 0)
            {
                System.out.println("The " + this.getType() + " won the battle!\n");
                return false;
            }
            else if (opponent.getHealth() <= 0)
            {
                System.out.println("The " + opponent.getType() + " won the battle!\n");
                return true;
            }

            opponent.setHealth((byte) (opponent.getHealth() - (this.getAttack() -
                    opponent.getDefense())));
            this.setHealth((byte) (this.getHealth() - (opponent.getAttack() -
                    this.getDefense())));
        }
    }

    public static boolean autoAttack(Character attacker, Character defender)
    {
        return attacker.attack(defender);
    }
}
