/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTVariableDeclaratorId.java */

package net.sourceforge.pmd.lang.java.ast;

import java.util.List;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.symboltable.VariableNameDeclaration;
import net.sourceforge.pmd.lang.symboltable.NameOccurrence;

public class ASTVariableDeclaratorId extends AbstractJavaTypeNode {

    private int arrayDepth;
    private VariableNameDeclaration nameDeclaration;
    private boolean explicitReceiverParameter = false;

    public ASTVariableDeclaratorId(int id) {
        super(id);
    }

    public ASTVariableDeclaratorId(JavaParser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public VariableNameDeclaration getNameDeclaration() {
        return nameDeclaration;
    }

    public void setNameDeclaration(VariableNameDeclaration decl) {
        nameDeclaration = decl;
    }

    public List<NameOccurrence> getUsages() {
        return getScope().getDeclarations().get(nameDeclaration);
    }

    public void bumpArrayDepth() {
        arrayDepth++;
    }

    public int getArrayDepth() {
        return arrayDepth;
    }

    public boolean isArray() {
        return arrayDepth > 0;
    }

    public boolean isExceptionBlockParameter() {
        return jjtGetParent().jjtGetParent() instanceof ASTTryStatement;
    }

    public void setExplicitReceiverParameter() {
        explicitReceiverParameter = true;
    }

    public boolean isExplicitReceiverParameter() {
        return explicitReceiverParameter;
    }

    public Node getTypeNameNode() {
        if (jjtGetParent() instanceof ASTFormalParameter) {
            return findTypeNameNode(jjtGetParent());
        } else if (jjtGetParent() instanceof ASTLambdaExpression) {
            // lambda expression with lax types. The type is inferred...
            return null;
        } else if (jjtGetParent().jjtGetParent() instanceof ASTLocalVariableDeclaration
                || jjtGetParent().jjtGetParent() instanceof ASTFieldDeclaration) {
            return findTypeNameNode(jjtGetParent().jjtGetParent());
        }
        return null;
    }

    /**
     * Determines the type node of this variable id.
     * 
     * @return the type node or <code>null</code> if there is no explicit type.
     */
    public ASTType getTypeNode() {
        if (jjtGetParent() instanceof ASTFormalParameter) {
            return ((ASTFormalParameter) jjtGetParent()).getTypeNode();
        } else if (jjtGetParent() instanceof ASTLambdaExpression) {
            // lambda expression with lax types. The type is inferred...
            return null;
        } else {
            Node n = jjtGetParent().jjtGetParent();
            if (n instanceof ASTLocalVariableDeclaration || n instanceof ASTFieldDeclaration) {
                return n.getFirstChildOfType(ASTType.class);
            }
        }
        return null;
    }

    private Node findTypeNameNode(Node node) {
        int i = 0;
        while (node.jjtGetChild(i) instanceof ASTAnnotation) {
            // skip annotations
            i++;
        }
        ASTType typeNode = (ASTType) node.jjtGetChild(i);
        return typeNode.jjtGetChild(0);
    }
}
