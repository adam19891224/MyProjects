package com.adam.Interest.javaknowledge.Strings;

import java.io.UnsupportedEncodingException;

/**
 * 这是一个关于string类的所有方法的介绍
 *
 */
public class StringsUtill {

//	private static String string = "my heart will go on";
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//charAt 返回指定数字下标对应的字符，注意下标从0开始，到string.length - 1 结束
//		char c = string.charAt(3);
		
		//codePointAt 返回指定数字下标对应的字符的unicode码，例如下面，第三号的字符是h，h的unicode码是104
//		int c = string.codePointAt(3);
		
		//codePointBefore 返回指定数字下标之前的字符对应的unicode码，注意，此时下标不能小于1，如果填0的话，0之前就是-1，-1没有，所以会暴异常
		//但是此时就可以写string.length了，因为string.length之前的正好是最后一个字符
		//如下，3对应的是h，则h之前的是一个空格 ,所以unicode码是32
//		int c = string.codePointBefore(3);
		
		//codePointCount 返回两个下标之间的字符的unicode代码数，可以简单的理解为两个坐标之间的字符数
		//从起始坐标开始算起，到结束坐标之前的那一个字符
		//如下，2为起始坐标，12为结束坐标，则实际就是从2到11，正好10个
//		int c = string.codePointCount(2, 12);
		
		//offsetByCodePoints 返回指定下标的偏移量的字符的下标位置，如下
		//此时2为指定下标，则它的偏移量为7，那么2向右移7个字符，该字符的下标就为9
		//从这里可以看出，虽然这个方法看似没有用，但是其主要是针对的某些用java来进行unicode码编程的人使用
//		int c = string.offsetByCodePoints(2, 7);
		
		
		
		//getChars 把被调用的字符串通过设置起始位置和偏移量进行复制，复制到目标字符数组
//		String stringE = "意大利击败英格兰";
//		System.out.println("使用之前：     " + stringE);
//		//此时定义目标字符数组
//		char[] c = new char[stringE.length()];
//		//此时第一次修改，从被修改的字符串的第5号开始，到最后一号+1（该方法会自动-1去取，所以不用担心下标），所以此时被修改的字符串stringE
//		//的第五号是英字，长度-1即为最后一号，为兰，所以5到length实际的字符串子串为英格兰
//		//然后放入新的字符数组，从0号开始
//		stringE.getChars(5, stringE.length(), c, 0);
//		//此时c字符数组的值就为英格兰
//		System.out.println("第一次修改：      " + new String(c));
//		//此时第二次修改，从stringE的3号开始，为击，5号-1为4号，即败，所以此时3到5的字符串子传实际为击败
//		//然后放入新字符数组，注意此时不能从0开始了，不然就把原来的英格兰覆盖了，所以从3开始
//		//此时有人问，为什么不直接从c.length开始，因为前面我们定义了char的length为stringE的长度，是8，所以
//		//如果这里用c.length，实际代码就是getChars(3, 5, c, 8)，显然c的长度才为8，但是我们却从8开始复制，明显越界
//		stringE.getChars(3, 5, c, 3);
//		//此时c字符数组的值就为英格兰击败
//		System.out.println("第二次修改：      " + new String(c));
//		//第三次同理，只是取出的是意大利这个子串，然后复制到char, 从5开始
//		stringE.getChars(0, 3, c, 5);
//		//此时c数组就为英格兰击败意大利了，就实现了字符串的颠倒
//		System.out.println("第三次修改：      " + new String(c));
		
		//getBytes， 将指定字符串转换为Byte数组并返回， 打印可得，实际就是把string转换为了对应的unicode码的Byte数组
//		byte[] b = string.getBytes();
//		System.out.println(Arrays.toString(b));

		//contentEquals用于比较两个字符串的顺序和值，通常与stringbuffer等进行直接的比较
//		String stringE = "my heart will go on";
//		System.out.println(string.equals(stringE));
//		System.out.println(string.contentEquals(stringE));
//		StringBuffer sb = new StringBuffer(stringE);
//		System.out.println(string.contentEquals(sb));
//		System.out.println(string.equals(sb));

		//equalsIgnoreCase 忽略大小写比较，如下，第一个我们用大写的stringE去比较string，用忽略比较，返回true, 但第二个我们直接用equal, 返回false
//		String stringE = "MY HEART WILL GO ON";
//		System.out.println(stringE.equalsIgnoreCase(string));
//		System.out.println(stringE.equals(string));

		//compareTo 比较两个字符串的Unicode码是否相等，相等返回0，且compareTo只会在equal返回true时才返回0
		//compareToIgnoreCase 忽略大小写的比较， 看源码可得，它在内部调用了toUpperCase和toLowerCase来转换之后比较
//		String stringE = "My heart will go on";
//		System.out.println(stringE.compareTo(string));
//		System.out.println(stringE.compareToIgnoreCase(string));

		//regionMatches 比较两个字符串的指定子串是否相等
		//如下面，我们从string的5开始，即a，然后同样从stringE的5开始，也是a，比较后面的三个字符，即art，发现相等，就返回true
		//此时我们定义stringA为art，来验证是否相等，但是注意，此时stringA的起始字符应该是0了
		//我们就可以用这个方法来验证子串，如regio(5, target, 0, target.length())
//		String stringE = "my heart will go on";
//		String stringA = "ART";
//		System.out.println(string.regionMatches(5, stringE, 5, 3));
//		System.out.println(string.regionMatches(5, stringA, 0, stringA.length()));
		//下面的方法多了一个boolean参数，表示是否忽略大小写
//		System.out.println(string.regionMatches(true, 5, stringA, 0, stringA.length()));

		//substring 截取字符串，从起始下标开始，到结束下标之前，下面写的是0,2,表示从0开始，到2 - 1 = 1位置
		//实际上取的值是0, 1两个位置的字符，而不是0, 1, 2三个位置
//		System.out.println(string.substring(0, 2));

		//replace 替换原字符为新字符
//		System.out.println(string.replace(" ", "  &  "));

		//matches 正则匹配
//		System.out.println(string.matches("[a-zA-Z]*"));

		//contains 验证指定字符串是否包含目标字符串，同indexof类似，但是Indexof可以得到子串在父串的起始位置，而contains只能得到true或fale
//		String stringE = "my heart";
//		System.out.println(string.contains(stringE));

		//转换大小写
//		System.out.println(string.toUpperCase());
//		System.out.println(string.toLowerCase());

	}


}
