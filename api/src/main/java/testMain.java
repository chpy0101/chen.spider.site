public class testMain {
	public static void main(String[] args) {

		int x = -5;
		System.out.print(~-1);
		System.out.print(~0);
		System.out.print(~1);
		//负数右移高位补1
		//负数实际运算用补码 符号位后按位取反+1计算完再减一。按位取反
		// 所以需要再或一个数。正数为1.0为0
		//正数取反 = 加一变负 ,~8 -> 00001000 ->11110111->10001000->10001001 =-9
		// 负数取反 加1变正   ~-8-> 10001000 -> 11110111->11111000->00000111 = 7
		//右移31位负数为-1,正数和0都是0。或操作 -1与任何数相或。得出仍为-1
		//取反 value+1 然后去相反数，+1再右移是为了区分0和1取反为-1 和-2 然后+1区分0 和 正数为 0和-1，右移31位 保持正数计算出为-1，0不变还是=0
		//最后再取反0变为-1，正数变为0，最后+1 0 = 0 正数=1
		System.out.print(x >> 31 | (~((~x + 1) >> 31) + 1));

	}

}

