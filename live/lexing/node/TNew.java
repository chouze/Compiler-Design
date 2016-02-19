/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class TNew extends Token
{
    public TNew()
    {
        super.setText("new");
    }

    public TNew(int line, int pos)
    {
        super.setText("new");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNew(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNew(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNew text.");
    }
}
