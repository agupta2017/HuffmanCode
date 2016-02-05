public class Runner
{
	private static HuffmanTree tree;
	
	public static void main(String[] args)
	{
		tree = new HuffmanTree("Miss Mississipi");
		System.out.println(tree.getTree());
		System.out.println(encode("Miss",tree.getTree()));
		System.out.println(decode(encode("Miss",tree.getTree()),tree.getTree()));
	}
	
	public static String encode(String input, HuffmanNode root)
	{
		String encoded = "";
		char[] c = input.toCharArray();
		for(int i = 0; i< c.length; i++)
		{
			encoded += findCode(c[i], root);
		}
		return encoded;
	}
	
	private static String findCode(char c, HuffmanNode root)
	{
		HuffmanNode current = root;
		String code = "";
		while(!current.isLeaf())
		{
			if(current.left().value().contains("" + c))
			{
				code += '0';
				current = current.left();
			}
			else if(current.right().value().contains("" + c))
			{
				code += '1';
				current = current.right();
			}
		}
		return code;
	}
	
	public static String decode(String binary, HuffmanNode root)
	{
		char[] c = binary.toCharArray();
		HuffmanNode current = root;
		String decoded = "";
		for(int i = 0; i< c.length; i++)
		{
			if(current.isLeaf())
			{
				decoded += current.value();
				current = root;
				i--;
			}
			else if(c[i] == '0')
			{
				current=current.left();
			}
			else if(c[i] == '1')
			{
				current=current.right();
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		return decoded;
	}
}
/**
								sM pi:15
					s:6							M pi:9
										M p:4			i:5
									M:2		-p:2
										-:1		p:1
**/
