package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

public class ParseString {

    //char parseOperation() {}

    Number parseNumber(String str){
        if (isInteger(str)){
            return (new Integer(str));
        }
        else if (isLong(str)){
            return (new Long(str));
        }
        else if (isDouble(str)){
            return (Double.valueOf(str));
        }
        return null;
    }

    boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    boolean isLong(String str){
        try{
            Long.parseLong(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    boolean isDouble(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

}
