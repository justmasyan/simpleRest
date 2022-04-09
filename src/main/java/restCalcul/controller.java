package restCalcul;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controller {
    public static class inside{
        private String answer;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
    @RequestMapping(value = "/calc",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public inside restComputing(String expression){

        inside str = new inside();
        str.setAnswer(expression);
        try {
            if (expression.contains(" ")) {
                int ind_oper = expression.indexOf(" ");
                float num1 = Float.valueOf(expression.substring(0,ind_oper));
                float num2 = Float.valueOf(expression.substring(ind_oper+1,expression.length()));
                str.setAnswer(computing(num1,num2,'+'));
            }
            else if (expression.contains("-")) {
                int ind_oper = expression.indexOf("-");
                float num1 = Float.valueOf(expression.substring(0,ind_oper));
                float num2 = Float.valueOf(expression.substring(ind_oper+1,expression.length()));
                str.setAnswer(computing(num1,num2,'-'));
            } else if (expression.contains("*")) {
                int ind_oper = expression.indexOf("*");
                float num1 = Float.valueOf(expression.substring(0,ind_oper));
                float num2 = Float.valueOf(expression.substring(ind_oper+1,expression.length()));
                str.setAnswer(computing(num1,num2,'*'));
            }
            else if(expression.contains("/")){
                int ind_oper = expression.indexOf("/");
                float num1 = Float.valueOf(expression.substring(0,ind_oper));
                float num2 = Float.valueOf(expression.substring(ind_oper+1,expression.length()));
                str.setAnswer(computing(num1,num2,'/'));
            }
            else
                str.setAnswer("There isn`t operation sign");

        }catch (NumberFormatException exc){
            str.setAnswer("NotCorrectInput");
        }




        return str;
    }

    private String computing(float num1,float num2,char operation){

        if(operation == '/' && num2 == 0)
            return "Error.Divided on 0";

        switch (operation){
            case '+': return String.valueOf(num1+num2);
            case '-':return String.valueOf(num1-num2);
            case'*':return String.valueOf(num1*num2);
            case'/':return String.valueOf(num1/num2);
            default: return "Error";
        }

    }
}
