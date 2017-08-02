
package eveniment.Entities.Enums;

public class Convertor {
    public static short ToShort(AccessLevel level){
        if(level == AccessLevel.Regular)
            return 0;
        if(level == AccessLevel.Operator)
            return 1;
        return -1;
    }
}
