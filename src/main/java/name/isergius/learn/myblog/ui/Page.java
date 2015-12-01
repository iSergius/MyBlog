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
    private Long field = 2L;
    private Long page = 1L;
    private long pagePlace = 1;

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

    public List<T> result() {
        long index = getIndex(page);
        return portion.result(index, size);
    }

    public Long count() {
        return pageCount;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPage() {
        return this.page;
    }

    public void setPagination(Long field) {
        this.field = field;
    }

    public Long beginPagination() {
        long result = 1;
        if (field * 2 + pagePlace > pageCount) result = 1;
        else if (page > pageCount - field) result = pageCount - (field * 2 + pagePlace);
        else if (page > field) result = page - field;
        return result;
    }

    public Long endPagination() {
        long result = page + field;
        if (pageCount < (field * 2 + pagePlace)) result = pageCount;
        else if (page > pageCount - field) result = pageCount;
        else if (page <= field) result = field * 2 + pagePlace;
        return result;
    }

    public Long forwardPagination() {
        long result = endPagination() + 1;
        if (pageCount < endPagination() + 1) result = pageCount;
        return result;
    }

    public Long backwardPagination() {
        long result = beginPagination() - 1;
        if (beginPagination() == 1) result = 1;
        return result;
    }

    private Long getIndex(Long page) {
        page = abs(page);
        if (page > pageCount) page = pageCount--;
        else if (page == 0) return 0L;
        else page--;
        return size * page;
    }

}
