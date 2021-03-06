/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import java.util.*;
import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AExtendsClassdecldeff extends PClassdecldeff
{
    private TExtends _extends_;
    private TIdentifier _identifier_;
    private TLeftBrace _leftBrace_;
    private final LinkedList<PVardecl> _vardecl_ = new LinkedList<PVardecl>();
    private final LinkedList<PMethoddecl> _methoddecl_ = new LinkedList<PMethoddecl>();
    private TRightBrace _rightBrace_;

    public AExtendsClassdecldeff()
    {
        // Constructor
    }

    public AExtendsClassdecldeff(
        @SuppressWarnings("hiding") TExtends _extends_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TLeftBrace _leftBrace_,
        @SuppressWarnings("hiding") List<PVardecl> _vardecl_,
        @SuppressWarnings("hiding") List<PMethoddecl> _methoddecl_,
        @SuppressWarnings("hiding") TRightBrace _rightBrace_)
    {
        // Constructor
        setExtends(_extends_);

        setIdentifier(_identifier_);

        setLeftBrace(_leftBrace_);

        setVardecl(_vardecl_);

        setMethoddecl(_methoddecl_);

        setRightBrace(_rightBrace_);

    }

    @Override
    public Object clone()
    {
        return new AExtendsClassdecldeff(
            cloneNode(this._extends_),
            cloneNode(this._identifier_),
            cloneNode(this._leftBrace_),
            cloneList(this._vardecl_),
            cloneList(this._methoddecl_),
            cloneNode(this._rightBrace_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExtendsClassdecldeff(this);
    }

    public TExtends getExtends()
    {
        return this._extends_;
    }

    public void setExtends(TExtends node)
    {
        if(this._extends_ != null)
        {
            this._extends_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._extends_ = node;
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public TLeftBrace getLeftBrace()
    {
        return this._leftBrace_;
    }

    public void setLeftBrace(TLeftBrace node)
    {
        if(this._leftBrace_ != null)
        {
            this._leftBrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftBrace_ = node;
    }

    public LinkedList<PVardecl> getVardecl()
    {
        return this._vardecl_;
    }

    public void setVardecl(List<PVardecl> list)
    {
        this._vardecl_.clear();
        this._vardecl_.addAll(list);
        for(PVardecl e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public LinkedList<PMethoddecl> getMethoddecl()
    {
        return this._methoddecl_;
    }

    public void setMethoddecl(List<PMethoddecl> list)
    {
        this._methoddecl_.clear();
        this._methoddecl_.addAll(list);
        for(PMethoddecl e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public TRightBrace getRightBrace()
    {
        return this._rightBrace_;
    }

    public void setRightBrace(TRightBrace node)
    {
        if(this._rightBrace_ != null)
        {
            this._rightBrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightBrace_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._extends_)
            + toString(this._identifier_)
            + toString(this._leftBrace_)
            + toString(this._vardecl_)
            + toString(this._methoddecl_)
            + toString(this._rightBrace_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._extends_ == child)
        {
            this._extends_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._leftBrace_ == child)
        {
            this._leftBrace_ = null;
            return;
        }

        if(this._vardecl_.remove(child))
        {
            return;
        }

        if(this._methoddecl_.remove(child))
        {
            return;
        }

        if(this._rightBrace_ == child)
        {
            this._rightBrace_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._extends_ == oldChild)
        {
            setExtends((TExtends) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._leftBrace_ == oldChild)
        {
            setLeftBrace((TLeftBrace) newChild);
            return;
        }

        for(ListIterator<PVardecl> i = this._vardecl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PVardecl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        for(ListIterator<PMethoddecl> i = this._methoddecl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PMethoddecl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._rightBrace_ == oldChild)
        {
            setRightBrace((TRightBrace) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
