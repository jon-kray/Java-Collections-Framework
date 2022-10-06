package list;

import java.util.List;

public interface StoreBook {

    public void add(Book book);

    public List<Book> findAllByNamed(String named);
}
