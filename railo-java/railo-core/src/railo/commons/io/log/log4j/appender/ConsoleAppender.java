package railo.commons.io.log.log4j.appender;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

public class ConsoleAppender extends WriterAppender {

	public ConsoleAppender() {
	}
	public ConsoleAppender(Layout layout) {
		setLayout(layout);
	}
	
	public ConsoleAppender(PrintWriter pw,Layout layout) {
		setWriter(pw);
		setLayout(layout);
	}
	
	public ConsoleAppender(PrintStream ps,Layout layout) {
		setWriter(new PrintWriter(ps));
		setLayout(layout);
	}
}