import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Queue<T> {

	private final Stack<T> in = new Stack<>();
	private final Stack<T> out = new Stack<>();

	public void put(T data) {
		in.push(data);
	}

	public T take() {
		if (out.size() == 0) {
			while (in.size() > 0) {
				out.push(in.pop());
			}
		}

		try {
			return out.pop();
		} catch (EmptyStackException e) {
			throw new NoSuchElementException();
		}
	}

	public static void main(String[] args) {
		Queue<Integer> q = new Queue<>();

		try {
			q.take();
			assert false;
		} catch (NoSuchElementException expected) {}

		q.put(1);
		assert q.take() == 1;
		q.put(2);
		q.put(3);
		q.put(4);
		assert q.take() == 2;
		q.put(5);
		assert q.take() == 3;
		q.put(6);
		q.put(7);
		assert q.take() == 4;
		assert q.take() == 5;
		assert q.take() == 6;
		assert q.take() == 7;

		try {
			q.take();
			assert false;
		} catch (NoSuchElementException expected) {}
	}

}