import { navio } from "@john-guerra/navio"
viewof selected = navio(papers)
forceBoundary = require("d3-force-boundary")
netClustering = require("netclustering")

{
  const nodes = network.nodes.map(d => ({ ...d })),
      links = network.links.map(l => ({ source : l.source.id, target: l.target.id, value: l.value }));


  const svg = d3.create("svg").attr("viewBox", [0, 0, width, height]);
  //return nodes;

  const lines = svg.selectAll("line")
  .data(links)
  .join("line")
  .style("stroke", "#ccc")
  .style("stroke-opacity", l => opacity(l.value));


  const text = svg
  .selectAll("text")
  .data(nodes)
  .join("text")
  .attr("fill", "#333")
  .style("font-size", d => size(d.value) + "pt")
  .style("fill", d => color(d.cluster))
  .text(d => d.id);

  const ticked = () => {
    //circles.attr("cx", d => d.x).attr("cy", d => d.y);
    text.attr("x", d => d.x).attr("y", d => d.y);
    lines
    .attr("x1", l => l.source.x)
    .attr("y1", l => l.source.y)
    .attr("x2", l => l.target.x)
    .attr("y2", l => l.target.y);
  };

  const simulation = d3.forceSimulation(nodes)
  .force("charge", d3.forceManyBody().strength(-20))
  .force("center", d3.forceCenter(width / 2, height / 2).strength(0.5))
  .force("link", d3.forceLink(links).id(d => d.id))
  .force("boundary", forceBoundary(3, 3, width, height))
  .on("tick", ticked);

  text.call(drag(simulation));
  invalidation.then(() => simulation.stop());

  return svg.node();

}

drag = simulation => {
  function dragstarted(event) {
    if (!event.active) simulation.alphaTarget(0.3).restart();
    event.subject.fx = event.subject.x;
    event.subject.fy = event.subject.y;
  }

  function dragged(event) {
    event.subject.fx = event.subject.x;
    event.subject.fy = event.subject.y;
  }

  function draggended(event) {
    if (!event.active) simulation.alphaTarget(0);
    event.subject.fx = null;
    event.subject.fy = null;
  }

  return d3
  .drag()
  .on("start", dragstarted)
  .on("drag", dragged)
  .on("end", draggended);
}

color = d3.scaleOrdinal(d3.schemeCategory10)

opacity = d3.scaleLinear()
.domain(d3.extent(network.links, d => d.value))
.range([0.1, 1])

size = d3.scaleLinear()
.domain(d3.extent(network.nodes, d => d.value))
.range([4, 18])

height = 400

network = {
  const dLinks = new Map();
  for (let t of selected) {
    const authorname = t.AuthorNames;
    const hashtags = authorname.split(";");
    for (let i = 0; i < hashtags.length; i += 1) {
      for (let j = i + 1; j < hashtags.length; j += 1) {
        const key = getKey (
            hashtags[i],
            hashtags[j]
        );
        if (!dLinks.has(key)) dLinks.set(key, 0);

        dLinks.set(key, dLinks.get(key) + 1);
      }
    }
  }

    const dNodes = new Map();
  let links = [];
  for (let [l, v] of dLinks) {
  const [source, target] = l.split("~");

  const s = findOrAdd(dNodes, source);
  const t = findOrAdd(dNodes, target);

  dNodes.set(source, ((s.value += 1), s));
  dNodes.set(target, ((t.value += 1), t));
  links.push({source: s, target: t, value: v});
  }

  const network = {nodes: Array.from(dNodes.values()), links};
  //netClustering.cluster(network.nodes, network.links);
  return network;
}

getKey = (a, b) => (a >= b ? `${a}~${b}` : `${b}~${a}`)

style = html`<style>svg text {
    font-family: sans-serif;
    text-anchor: middle; 
    cursor: pointer 
} `

findOrAdd = (dNodes, n) => {
  if (!dNodes.has(n)) dNodes.set(n, {id : n, value : 0});
  return dNodes.get(n);
}

papers = FileAttachment("coauthor.json").json()

d3 = require("d3@6")