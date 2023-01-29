
package expression;
import java.util.Scanner;
/**
 *
 * @author Usman_Aslam
 */
class Stack {
    int top;
     String[] l = new String[20];
    Stack()
    {
        top = -1;
       
    }
    void push(String d)
    {
        if(top < 9)
        {
            top = top + 1;
            l[top] = d;
        }
        else
            System.out.println("Stack OverFlow...!!!!!\n");
    }
    String pop()
    {
        if(top >= 0)
            return l[top--];
        else
            return "#";
    }
    String peek()
    {
        return l[top];
    }
    int getTop()
    {
        return top;
    }
    void display()
    {
        for(int i=0; i<=top; i++)
            System.out.println(l[i]);
    }
}
public class Expression extends Stack {
    String s;
    Expression(String ss)
    {
        s = ss;
    }
    int precedence(String cc)
    {
        switch(cc)
        {
            case"*":
            case"/":
                return 2;
            case"+":
            case"-":
                return 1;
            default:
                return 0;
        }
    }
    double solve_the_Expression()
    {
        
        String last_val;
        while(getTop() > 0)
        {
            System.out.println("Top" + getTop());
            last_val = pop();
            switch(pop())
            {
                case"+":
                    push((Double.parseDouble(pop()) + Double.parseDouble(last_val))+"");
                    break;
                case"-":
                    push((Double.parseDouble(pop()) - Double.parseDouble(last_val))+"");
                    break;
                case"*":
                    push((Double.parseDouble(pop()) * Double.parseDouble(last_val))+"");
                    break;
                case"/":
                    push((Double.parseDouble(pop()) / Double.parseDouble(last_val))+"");
                    break;            
            }
        }
        return  Double.parseDouble(pop());
    }
    void parse_the_Expression()
    {
        char ch;
        String last_val, last_op;
        int len = s.length();
        
        for(int i = 0; i<len; i++)
        {
            ch = s.charAt(i);
            if(ch>= '0' && ch <= '9')
            {
                System.out.println("char: " + ch);
                push(""+ch);
            }
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/')
            {
                System.out.println("Operator: " + ch);
                if(getTop() == 0)
                    push(""+ch);
                else
                {
                    last_val = pop();
                    last_op = pop();
                    
                    if(precedence(""+ch) > precedence(last_op))
                    {
                        push(last_op);
                        push(last_val);
                    }
                    else
                    {
                        switch(last_op)
                        {
                            case"+":
                                push((Double.parseDouble(pop()) + Double.parseDouble(last_val))+"");
                                break;
                            case"-":
                                push((Double.parseDouble(pop()) - Double.parseDouble(last_val))+"");
                                break;
                            case"*":
                                push((Double.parseDouble(pop()) * Double.parseDouble(last_val))+"");
                                break;
                            case"/":
                                push((Double.parseDouble(pop()) / Double.parseDouble(last_val))+"");
                                break;
                        }
                    }
                    push(""+ch);
  
                }
            }
            else
            {
                System.out.println("Invalid Expression...!!!\n");
                break;
            }
            
        }
    }
    public static void main(String[] args) {
       String exp;
       double result;
       Scanner obj = new Scanner(System.in);
       System.out.println("Enter Expression: ");
       exp = obj.nextLine();
       
       Expression eObj = new Expression(exp);
       
       eObj.parse_the_Expression();
       result = eObj.solve_the_Expression();
       
       System.out.println("Result of Expression: " + result);
    }
    
}
