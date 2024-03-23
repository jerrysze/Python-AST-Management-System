package hk.ust.comp3021.utils;

import java.util.*;

public class XMLNode {
    private String tagName;
    private Map<String, String> attributes;
    private List<XMLNode> children;
    private XMLNode parent;

    private int lineNo;

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public XMLNode(String tagName) {
        this.tagName = tagName;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public boolean hasAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }

    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    public List<XMLNode> getChildren() {
        return children;
    }

    public XMLNode getChildByIdx(int idx) {
        return children.get(idx);
    }

    public int getNumChildren() {
        return children.size();
    }

    public void addChild(XMLNode child) {
        children.add(child);
        child.setParent(this);
    }

    public XMLNode getParent() {
        return parent;
    }

    public void setParent(XMLNode parent) {
        this.parent = parent;
    }


    public void writeTo(StringBuffer sb) {
        writeTo(sb, 0);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        writeTo(sb);
        return sb.toString();
    }
    public void writeTo(StringBuffer sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        sb.append("<").append(tagName);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        if (children.isEmpty()) {
            sb.append("/>\n");
        } else {
            sb.append(">\n");
            for (XMLNode child : children) {
                child.writeTo(sb, indent + 1);
            }
            for (int i = 0; i < indent; i++) {
                sb.append("  ");
            }
            sb.append("</").append(tagName).append(">\n");
        }
    }
}
