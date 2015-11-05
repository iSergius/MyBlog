package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.Model;
import name.isergius.learn.myblog.dao.Portion;

import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
public class Page<T extends Model> {

    private Portion<T> portion;
    private Long size;
    private Long pageCount;

    public Page(Portion<T> portion, Long size) {
        this.portion = portion;
        this.size = size;
        long countItems = portion.count();
        if (size == 0L) {
            pageCount = 1L;
        } else {
            pageCount = countItems / size;
            if (countItems % size != 0) pageCount++;
        }
    }

    public List<T> result(Long page) {
        long index = getIndex(page);
        return portion.result(index, size);
    }

    public Long count() {
        return pageCount;
    }

    private Long getIndex(Long page) {
        page = abs(page);
        if (page > pageCount) page = pageCount--;
        else if (page == 0) return 0L;
        else page--;
        return size * page;// 1 page = 0 index; 2 page = 10
    }

}
