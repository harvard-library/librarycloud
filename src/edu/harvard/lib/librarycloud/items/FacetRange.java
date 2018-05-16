package edu.harvard.lib.librarycloud.items;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


@XmlRootElement()
public class FacetRange {

  @XmlRootElement
  static class Count {
    private String name;
    private int count;

    @XmlElement()
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

  }

	private Object start;
  private Object end;
  private List<FacetRange.Count> counts = new ArrayList<FacetRange.Count>();

	public FacetRange() {

	}

  @XmlElement()
  public String getStart() {
    return this.start.toString();
  }

  public void setStart(Object start) {
    this.start = start;
  }

  @XmlElement()
  public String getEnd() {
    return this.end.toString();
  }

  public void setEnd(Object end) {
    this.end = end;
  }

  @XmlElement(name = "range")
  public List<FacetRange.Count> getCounts() {
    return counts;
  }

  public void addCount(String name, int count) {
    FacetRange.Count c = new FacetRange.Count();
    c.setName(name);
    c.setCount(count);
    this.counts.add(c);
  }

}
