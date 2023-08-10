import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        //System.out.println(tags);
        Stack<HtmlTag> stack = new Stack<>();
        boolean remainingClosingTag = false;

        for (HtmlTag tag: tags) {
            //System.out.println(tag);
            if(tag.isOpenTag() && !tag.isSelfClosing()) {
                // isOpenTag
                stack.push(tag);
            } else {
                // isCloseTag
                remainingClosingTag = true;
                if(stack.size() > 0 && tag.matches(stack.peek())){
                    stack.pop();
                    remainingClosingTag = false;
                } else {
                    // si el orden de la etiqueta de cierre no es el correcto
                    break;
                }
            }
        }

        return remainingClosingTag ? null : stack;
    }

    public static void main(String[] args) {
        try {
            Queue<HtmlTag> tagsFromHtmlFile = HtmlReader.getTagsFromHtmlFile("C:\\Users\\Administrator\\Documents\\personal\\03 BACKEND\\workshopEstructuraDatos\\src\\testing.html");
            Stack<HtmlTag> stack = isValidHtml(tagsFromHtmlFile);
            System.out.println(stack);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
