/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

/* Generated By:JJTree: Do not edit this line. ASTPackageSpecification.java Version 4.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY= */

package net.sourceforge.pmd.lang.plsql.ast;

public class ASTPackageSpecification extends net.sourceforge.pmd.lang.plsql.ast.AbstractPLSQLNode
        implements OracleObject {
    public ASTPackageSpecification(int id) {
        super(id);
    }

    public ASTPackageSpecification(PLSQLParser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(PLSQLParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Gets the name of the Oracle Object.
     * 
     * @return a String representing the name of the Oracle Object
     */
    @Override
    public String getObjectName() {
        return this.getImage();
    }
}
/*
 * JavaCC - OriginalChecksum=f002db7d2f63fdbb3a5339191c15692f (do not edit this
 * line)
 */
