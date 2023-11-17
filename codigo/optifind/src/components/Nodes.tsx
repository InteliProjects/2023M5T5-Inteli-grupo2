import { Position } from "reactflow";
//Nós mockados para testar o front
const borderCss = {
  backgroundColor: "#aff4c6",
  border: "2px solid #8cc39e ",
};
const curveNodeStart = {
  background: "#ffd233",
  color: "black",
  width: 70,
  borderRadius: "18px",
};

const rectangelNode = {
  color: "black",
  ...borderCss,
  backgroundColor: "#EC858A",
  border: "2px solid #8cc39e ",
};

const curveNodeEnd = {
  background: "#ffd233",
  color: "black",
  width: 70,
  borderRadius: "18px",
};

export const nodes = [
  // Start Node
  {
    id: "node-1",
    type: "start",
    data: {
      label: "Entrada",
    },
    style: curveNodeStart,
    position: { x: -100, y: 0 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  //
  {
    id: "node-2",
    type: "",
    data: {
      label: "MixProof",
    },
    style: rectangelNode,
    position: { x: 50, y: 0 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-3",
    type: "pyramid",
    data: {
      label: "Tank",
    },
    position: { x: 250, y: -32 },
    draggable: true,
  },
  {
    id: "node-4",
    type: "",
    data: {
      label: "MixProof",
    },
    style: rectangelNode,
    position: { x: 225, y: -200 },
    sourcePosition: Position.Right,
    targetPosition: Position.Bottom,
    draggable: true,
  },
  {
    id: "node-7",
    type: "pyramid",
    data: {
      label: "Tank",
    },
    position: { x: 950, y: -50 },
    draggable: true,
  },
  {
    id: "node-8",
    type: "",
    data: {
      label: "MixProof",
    },
    style: rectangelNode,
    position: { x: 1150, y: 0 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-9",
    type: "start",
    data: {
      label: "Entrada",
    },
    style: curveNodeStart,
    position: { x: 1350, y: 0 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-10",
    type: "",
    data: {
      label: "MixProof",
    },
    style: rectangelNode,
    position: { x: 1350, y: -200 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-12",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1550, y: -200 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-13",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 950, y: 200 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-15",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1350, y: 200 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-16",
    type: "pyramid",
    data: {
      label: "Tank",
    },
    position: { x: 1550, y: 200 },
    draggable: true,
  },
  {
    id: "node-17",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 950, y: 400 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-19",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1150, y: 600 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-20",
    type: "pyramid",
    data: {
      label: "Tank",
    },
    position: { x: 1350, y: 600 },
    draggable: true,
  },
  {
    id: "node-21",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1550, y: 600 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-22",
    type: "pyramid",
    data: {
      label: "Tank",
    },
    position: { x: 1750, y: 600 },
    draggable: true,
  },
  {
    id: "node-23",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1950, y: 600 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-24",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1350, y: 800 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-25",
    type: "",
    style: rectangelNode,
    data: {
      label: "MixProof",
    },
    position: { x: 1550, y: 800 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
  {
    id: "node-26",
    type: "",
    style: curveNodeEnd,
    data: {
      label: "End",
    },
    position: { x: 1350, y: 1000 },
    sourcePosition: Position.Right,
    targetPosition: Position.Left,
    draggable: true,
  },
];