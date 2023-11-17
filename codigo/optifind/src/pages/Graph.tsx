import Navbar from "../components/Navbar";
import { useCallback, useRef, useEffect, useState } from "react";
import ReactFlow, { MiniMap, Controls, Background, useNodesState, useEdgesState, addEdge} from "reactflow";
import "reactflow/dist/style.css";
import { FullPyramid } from "../components/CustomNode";
import { nodes as initialNodes } from "../components/Nodes";
import { edges as initialEdges } from "../components/Edges";
import { AddNode } from "../components/AddNode";
import { Button, TextField } from "@mui/material";
import { getAlgorithm, getAllPathsAlgorithm } from "../services/nodes";
import { getNodes, getEdges } from "../services/getNodes";
import ModalEdit from "../components/ModalEdit";
import { useCookies } from "react-cookie";
const myRPN = "ambov";

const curveNodeStart = {
  background: "#ffd233",
  color: "black",
  borderRadius: "18px",
};
const rectangelNode = {
  color: "black",
  backgroundColor: "#EC858A",
  border: "2px solid #8cc39e ",
};

const nodeTypes = {
  pyramid: FullPyramid,
};

const getId = () => `edge-${(Math.random() * 100000).toFixed(5)}`;

export default function Graph() {
  const [nodes, setNodes, onNodesChange] = useNodesState<any>(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);
  const [startAlgo, setStartAlgo] = useState("");
  const [endAlgo, setEndAlgo] = useState("");
  const reactFlowWrapper = useRef<any>(null);
  const connectingNodeId = useRef<any>(null);
  const onConnect = useCallback(
  (params: any) => setEdges((eds) => addEdge(params, eds)),
    [setEdges]
  );
  const [isModalOpen, setModalOpen] = useState(false);
  const [clickedNodeId, setClickedNodeId] = useState("");
  const [cookiesAuth] = useCookies(['authToken']);


  

  const handleMinAlgo = async () => {
    try {
      const { top, left } = reactFlowWrapper.current.getBoundingClientRect();
      let nodeNames = [""];
      nodeNames = await getAlgorithm(startAlgo, endAlgo);

      let allNodes = [];
      allNodes = await getNodes(cookiesAuth);

      let ourNodes = [];

      setEdges([]);
      setNodes([]);

      // filtra os nós
      for (let i = 0; i < allNodes.length; i++) {
        for (let j = 0; j < nodeNames.length; j++) {
          if (allNodes[i]["name"] == nodeNames[j]) {
            ourNodes.push(allNodes[i]);
          }
        }
      }

      // coloca os nós no canva
      for (let i = 0; i < ourNodes.length; i++) {
        const newNode = {
          id: `${ourNodes[i]["id"]}`,
            realId: `${ourNodes[i]["id"]}`,
            position: {
            x: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["x"] : top,
            y: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["y"] : left + 100*i,
          },
          data: { label: ourNodes[i]["name"], rpn: ourNodes[i]["rpn"], type: ourNodes[i]["type"], condicao: ourNodes[i]["properties"]["condicao"] ? ourNodes[i]["properties"]["condicao"] : "" },
          [ourNodes[i]["type"] == "Tanque" ? "type" : "style"]:
            ourNodes[i]["type"] == "Solenoide" ||
            ourNodes[i]["type"] == "MixProof"
              ? rectangelNode
              : ourNodes[i]["type"] == "Tanque"
              ? "pyramid"
              : curveNodeStart,
        };

        setNodes((nds) => nds.concat(newNode));
      }
      
      // coloca as arestas no canva
      for (let i = 0; i < ourNodes.length-1; i++) {
          setEdges((eds) => eds.concat({
            id: getId(),
            source: (ourNodes[i]["id"]).toString(),
            target: (ourNodes[i+1]["id"]).toString()
          }))
      }        
      
    } catch (err) {
      console.log("Erro tentando usar o algoritmo: ", err);
    }
  }

  const handleAllAlgo = async () => {
    try {
      const {top, left} = reactFlowWrapper.current.getBoundingClientRect();
      let allNodeNames = [];
      allNodeNames = await getAllPathsAlgorithm(startAlgo, endAlgo);
      
      let allNodes = [];
      allNodes = await getNodes(cookiesAuth);
      
      setNodes([]);
      setEdges([]);
      for (let n = 0; n < allNodeNames.length; n++) {
      let ourNodes: [] = [];

        // filtra os nós
        for (let i = 0; i < allNodes.length; i++) {
          for (let j = 0; j < allNodeNames[n].length; j++) {
            if (allNodes[i]["name"] == allNodeNames[n][j] && allNodes[i]["rpn"] == myRPN) {
              ourNodes.push(allNodes[i]);
            }
          }
        }

        // coloca esses nós no canva
        for (let i = 0; i < ourNodes.length; i++) {
          const newNode = {
            id: `${ourNodes[i]["id"]+50*n}`,
            realId: `${ourNodes[i]["id"]}`,
            position: {
              x: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["x"] : top + 400*n,
              y: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["y"] : left + 100*i,
            },
            data: { label: ourNodes[i]["name"], rpn: ourNodes[i]["rpn"], type: ourNodes[i]["type"], condicao: ourNodes[i]["properties"]["condicao"] ? ourNodes[i]["properties"]["condicao"] : ""},
            [ourNodes[i]["type"] == "Tanque" ? "type" : "style"]: ourNodes[i]["type"] == "Solenoide" || ourNodes[i]["type"] == "MixProof" ? rectangelNode : ourNodes[i]["type"] == "Tanque" ? "pyramid" : curveNodeStart,
          };

          setNodes((nds) => nds.concat(newNode));
        }

        // coloca as arestas no canva
        for (let i = 1; i < ourNodes.length-1; i++) {
          setEdges((eds) => eds.concat({
            id: getId(),
            source: (ourNodes[i-1]["id"]+50*n).toString(),
            target: (ourNodes[i]["id"]+50*n).toString(),
          }))
        }
      }
    } catch (err) {
      console.log("Erro tentando usar o algoritmo: ", err);
    }
  }

  function verifyIfEdgeExists(edge, ourEdges) {
    for (let i = 0; i < ourEdges.length; i++) {
      if (
        edge.start === ourEdges[i]["source"] &&
        edge.end === ourEdges[i]["end"]
      ) {
        return true;
      }
    }
    return false;
  }

  let loadNodes = async () => {
    try {
      const { top, left } = reactFlowWrapper.current.getBoundingClientRect();
      let allNodes = [];
      let allEdges = [];
      allNodes = await getNodes(cookiesAuth);
      allEdges = await getEdges(cookiesAuth);

      setNodes([]);
      setEdges([]);

      let ourNodes = [];

      //  filtrar os nós
      for (let i = 0; i < allNodes.length; i++) {
        if (allNodes[i]["rpn"] == myRPN) {
          ourNodes.push(allNodes[i]);
        }
      }

      // coloca os nós no canva
      for (let i = 0; i < ourNodes.length; i++) {
        const newNode = {
          id: `${ourNodes[i]["id"]}`,            
          realId: `${ourNodes[i]["id"]}`,
          position: {
            x: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["x"] : top + 10*i,
            y: (ourNodes[i]["position"]) ? ourNodes[i]["position"]["y"] : left + 10*i,
          },
          data: { label: ourNodes[i]["name"], rpn: ourNodes[i]["rpn"], type: ourNodes[i]["type"], condicao: ourNodes[i]["properties"]["condicao"] ? ourNodes[i]["properties"]["condicao"] : ""},
          [ourNodes[i]["type"] == "Tanque" ? "type" : "style"]:
            ourNodes[i]["type"] == "Solenoide" ||
            ourNodes[i]["type"] == "MixProof"
              ? rectangelNode
              : ourNodes[i]["type"] == "Tanque"
              ? "pyramid"
              : curveNodeStart,
        };

        setNodes((nds) => nds.concat(newNode));
    }

      // filtrar as arestas e as coloca no canva
      for (let i = 0; i < allEdges.length; i++) {
        if (allEdges[i]["r.rpn"] == myRPN) {
          let edgeNodeId = {
            start: "",
            end: "", 
          };
          for (let j = 0; j < ourNodes.length; j++) {
              if (!verifyIfEdgeExists(edgeNodeId, allEdges)){
                if (ourNodes[j]["name"] == allEdges[i]["startNode"]) {
                  edgeNodeId.start = ourNodes[j]["id"];
              }
                if (ourNodes[j]["name"] == allEdges[i]["endNode"]) {
                  edgeNodeId.end = ourNodes[j]["id"];
                }
                if (edgeNodeId.start != "" && edgeNodeId.end != "") {
                  break;
                }
              } else {
              break;
            }
          }
          setEdges((eds) =>
          eds.concat({
            id: getId(),
            source: (edgeNodeId.start).toString(),
            target: (edgeNodeId.end).toString(),
          })
        );
        }
      }

    } catch (err) {
      console.log("Erro gerando o canva: ", err);
    }
  };

  useEffect(() => {
      try{
      loadNodes();
    } catch (err) {console.log("Erro gerando o canva: ", err)}
  }, []);

  const createNode = useCallback((event: any) => {
    loadNodes();
  }, []);

  const createNodeAntigo = useCallback((event: any) => {
    const targetIsPaneTerminal =
      event.target.classList.contains("react-terminal");
    const targetIsPaneProcess =
      event.target.classList.contains("react-process");
    const targetIsPaneDecision =
      event.target.classList.contains("react-decision");
    if (targetIsPaneTerminal) {
      // we need to remove the wrapper bounds, in order to get the correct position
      const curveNodeStart = {
        background: "#ffd233",
        color: "black",
        // width: 70,
        borderRadius: "18px",
      };

      const { top, left } = reactFlowWrapper.current.getBoundingClientRect();
      const id = getId();

      const newNode = {
        id,
        position: {
          x: event.clientX - left - 1000,
          y: event.clientY - top + 200,
        },
        data: { label: `New Node ${id}}` },
        style: curveNodeStart,
      };

      setNodes((nds) => nds.concat(newNode));
      setEdges((eds) =>
        eds.concat({ id, source: connectingNodeId.current, target: id })
      );
    }
    if (targetIsPaneProcess) {
      const rectangelNode = {
        color: "black",
        backgroundColor: "#aff4c6",
        border: "2px solid #8cc39e ",
      };
      const { top, left } = reactFlowWrapper.current.getBoundingClientRect();
      const id = getId();
      const newNode = {
        id,
        position: {
          x: event.clientX - left - 1000,
          y: event.clientY - top + 300,
        },
        data: { label: `New Node ${id}}` },
        style: rectangelNode,
      };

      setNodes((nds) => nds.concat(newNode));
      setEdges((eds) =>
        eds.concat({ id, source: connectingNodeId.current, target: id })
      );
    }
    if (targetIsPaneDecision) {
      const { top, left } = reactFlowWrapper.current.getBoundingClientRect();
      const id = getId();
      const newNode = {
        id,
        position: {
          x: event.clientX - left - 1000,
          y: event.clientY - top + 400,
        },
        data: { label: `New Node ${id}}` },
        type: "pyramid",
      };

      setNodes((nds) => nds.concat(newNode));
      setEdges((eds) =>
        eds.concat({ id, source: connectingNodeId.current, target: id })
      );
    }
  }, []);

  const [nodeObj, setNodeObj] = useState({})

  const handleCloseModal = () => {
    setModalOpen(false);
    setClickedNodeId("");
  }

  const onNodeClick = (event: any, object: any) => {
    setClickedNodeId(object.realId);
    setNodeObj(object);
    setModalOpen(true);
    return [...nodes, ...edges];
  }

  return (
    <div style={{ width: "100vw", height: "100vh" }} ref={reactFlowWrapper}>
      <Navbar />
      <ReactFlow
        nodes={nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        fitView
        onNodeClick={onNodeClick}
        nodeTypes={nodeTypes}
      >
        <AddNode createNode={createNode} />
        <Controls />
        <MiniMap />
        <Background gap={12} size={1} />
      </ReactFlow>
      <div
        style={{
          display: "flex",
          position: "absolute",
          bottom: "2%",
          left: "50%",
          transform: "translateX(-50%)",
        }}
      >
        <TextField
          id="outlined-basic"
          label="Início"
          variant="outlined"
          style={{ marginRight: "16px", backgroundColor: "white" }}
          onChange={(e) => setStartAlgo(e.target.value)}
        />
        <TextField
          id="outlined-basic"
          label="Destino"
          variant="outlined"
          style={{ marginRight: "16px", backgroundColor: "white" }}
          onChange={(e) => setEndAlgo(e.target.value)}
        />
<div style={{display: "grid", gap: "3px"}}>
  <label> Calcular Rotas:</label>
<div >
            <Button style={{margin: "5px"}} variant="contained" color="info" onClick={handleMinAlgo}>
              Mínima
            </Button>
            <Button style={{margin: "5px"}} variant="contained" color="primary" onClick={handleAllAlgo}>Todas</Button>
</div>
</div>
      </div>
        <ModalEdit open={isModalOpen} onClose={handleCloseModal} nodeId={clickedNodeId} entireNode={nodeObj} />
    </div>
  );
}
