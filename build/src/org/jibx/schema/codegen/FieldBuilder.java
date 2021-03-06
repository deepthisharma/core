/*
 * Copyright (c) 2007-2009, Dennis M. Sosnoski. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of
 * JiBX nor the names of its contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jibx.schema.codegen;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * Abstract syntax tree field declaration builder. This adds convenience methods and control information to the base
 * builder.
 */
public class FieldBuilder extends BodyBuilderBase
{
    /** Field invocation. */
    private final FieldDeclaration m_field;
    
    /**
     * Constructor.
     * 
     * @param source Source
     * @param field Field declaration
     */
    public FieldBuilder(ClassBuilder source, FieldDeclaration field) {
        super(source, field);
        m_field = field;
    }
    
    /**
     * Get the field declaration.
     *
     * @return declaration
     */
    public FieldDeclaration getDeclaration() {
        return m_field;
    }
    
    /**
     * Set initializer expression for field declaration.
     *
     * @param expr base
     */
    public void setInitializer(ExpressionBuilderBase expr) {
        VariableDeclarationFragment frag = (VariableDeclarationFragment)m_field.fragments().get(0);
        frag.setInitializer(expr.getExpression());
    }
    
    /**
     * Set initializer as a string literal.
     *
     * @param value initializer
     */
    public void setStringInitializer(String value) {
        VariableDeclarationFragment frag = (VariableDeclarationFragment)m_field.fragments().get(0);
        StringLiteral literal = m_ast.newStringLiteral();
        literal.setLiteralValue(value);
        frag.setInitializer(literal);
    }
    
    /**
     * Set initializer as a number literal.
     *
     * @param value number literal
     */
    public void setNumberInitializer(String value) {
        VariableDeclarationFragment frag = (VariableDeclarationFragment)m_field.fragments().get(0);
        frag.setInitializer(m_ast.newNumberLiteral(value));
    }
}