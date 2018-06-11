package pattern.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerInputStreamDecorator extends FilterInputStream{

	public LowerInputStreamDecorator(InputStream in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		int c = super.read();
		return c == -1 ? c : Character.toLowerCase(c);
	}
	
	
}
