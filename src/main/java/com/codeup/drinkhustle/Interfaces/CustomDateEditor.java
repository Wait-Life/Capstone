package com.codeup.drinkhustle.Interfaces;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CustomDateEditor extends PropertyEditorSupport {

    public CustomDateEditor(SimpleDateFormat dateFormat, boolean b) {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}

