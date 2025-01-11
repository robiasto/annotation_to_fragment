package de.robiasto.app.infrastructure.fragment.plain.pagination;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Service
public class PageableSession {

    public Pageable getPageableFromSession(String attributeName, HttpServletRequest request, Pageable pageable) {
        HttpSession session = request.getSession();
        SerializablePageable pageableUpdate = new SerializablePageable(this.updateSession(attributeName, request, pageable));
        session.setAttribute(attributeName, pageableUpdate);

        return pageableUpdate.toPageable();
    }

    private Pageable updateSession(String attributeName, HttpServletRequest request, Pageable pageable) {
        if (request.getParameter("sort") != null) {
            return pageable;
        }

        Pageable pageableSession = this.getPageableFromSession(attributeName, request).withPage(pageable.getPageNumber());

        if (request.getParameter("page") != null) {
            return pageableSession.withPage(pageable.getPageNumber());
        }

        return pageableSession;
    }

    private Pageable getPageableFromSession(String attributeName, HttpServletRequest request) {
        HttpSession session = request.getSession();

        SerializablePageable pageableSession = (SerializablePageable) session.getAttribute(attributeName);

        if (pageableSession == null) {
            return PageRequest.of(0, 10, Sort.by(Sort.Order.asc("lastName"), Sort.Order.asc("firstName")));
        }

        return pageableSession.toPageable();
    }

    public static class SerializablePageable implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private final int pageNumber;
        private final int pageSize;
        private final Sort sort;

        public SerializablePageable(Pageable pageable) {
            this.pageNumber = pageable.getPageNumber();
            this.pageSize = pageable.getPageSize();
            this.sort = pageable.getSort();
        }

        public Pageable toPageable() {
            return PageRequest.of(pageNumber, pageSize, sort);
        }
    }
}
