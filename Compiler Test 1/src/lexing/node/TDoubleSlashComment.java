/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class TDoubleSlashComment extends Token
{
    public TDoubleSlashComment(String text)
    {
        setText(text);
    }

    public TDoubleSlashComment(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TDoubleSlashComment(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTDoubleSlashComment(this);
    }
}