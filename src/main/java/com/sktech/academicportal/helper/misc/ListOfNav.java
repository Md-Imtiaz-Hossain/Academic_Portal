package com.sktech.academicportal.helper.misc;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListOfNav {
    private List<NavBar> list = new ArrayList<>();

    public void add(NavBar n) {list.add(n);}

    public List<NavBar> getList(){return this.list;}

    public List<NavBar> getEditableList(){
        List<NavBar> ln = new ArrayList<>();
        for(int i=0; i<this.list.size(); i++){
            if(this.list.get(i).getNavText().equals("Home")) continue;
            ln.add(this.list.get(i));
        }
        return ln;
    }
}
