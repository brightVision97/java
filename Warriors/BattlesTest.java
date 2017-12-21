package DataStructures.PIK3.WarriorsTask;

public class BattlesTest
{
    public static void main(String[] args)
    {
        Warrior warrior1 = new Warrior((byte) 114, (byte) 98, (byte) 54);
        Archer archer1 = new Archer((byte) 129, (byte) 100, (byte) 85);
        Barbarian barbarian1 = new Barbarian((byte) 100, (byte) 114, (byte) 50);
        warrior1.attack(archer1);
        Character.autoAttack(archer1, barbarian1);
    }
}
