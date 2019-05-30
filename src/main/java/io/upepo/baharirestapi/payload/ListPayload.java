package io.upepo.baharirestapi.payload;

import java.util.ArrayList;
import java.util.List;

public class ListPayload<E> {
    private List<E> content = new ArrayList();


    public int getTotalElements()
    {
        return content.size();
    }

    public List<E> getContent() {
        return content;
    }

    public void setContent(List<E> content) {
        this.content = content;
    }
}
