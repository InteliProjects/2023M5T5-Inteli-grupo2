import { useState } from "react";
import { Panel } from "reactflow";
import CustomModal from "./ModalCreate";
import { createNode as postNode } from "../services/nodes";
import CircleIcon from "@mui/icons-material/Circle";
import DownloadIcon from "@mui/icons-material/Download";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import Box from "@mui/material/Box";
import Fab from "@mui/material/Fab";
import { uploadFiles } from "../services/uploadCSV";

export const AddNode = ({ createNode }: { createNode: any }) => {
  const [loading, setLoading] = useState(false);
  const [successNode, setSuccessNode] = useState(false);
  const [successEdge, setSuccessEdge] = useState(false);

  const handleFileUpload = (event, fileType) => {
    const file = event.target.files[0];
    if (file) {
      setLoading(true); // Inicia o carregamento
      setSuccessNode(false); // Reset o estado de sucesso de nós
      setSuccessEdge(false);
      try {
        if (fileType === "nodes") {
          uploadFiles(file, null);
          setSuccessNode(true); // Notifica sucesso de nós
        } else if (fileType === "edges") {
          uploadFiles(null, file);
          setSuccessEdge(true); // Notifica sucesso de arestas
        }
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false); // Termina o carregamento
      }
    }
  };
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [nodeType, setNodeType] = useState("");

  const handleOpenModal = (event: any) => {
    setIsModalOpen(true);
    setNodeType(event.target.className.split(" ")[1]);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setNodeType("");
  };

  const handleSubmitModal = async (
    name: string,
    rpn: string,
    volume: string,
    condicao: string
  ) => {
    // Chama a função createNode com os dados do formulário
    const response = await postNode(name, nodeType, rpn, volume, condicao);
    console.log(response);

    createNode();

    // Fecha o modal
    handleCloseModal();
  };

  return (
    <>
      <Panel position="bottom-left">
        <Box position={"left"} sx={{ "& > :not(style)": { m: 1 } }}>
          <Fab color="info" aria-label="add" component="label">
            <CircleIcon />
            <input
              type="file"
              hidden
              onChange={(e) => handleFileUpload(e, "nodes")}
            />
          </Fab>
          <Fab color="info" aria-label="add" component="label">
            <ArrowRightAltIcon />
            <input
              type="file"
              hidden
              onChange={(e) => handleFileUpload(e, "edges")}
            />
          </Fab>
          <Fab color="info" aria-label="add">
            <DownloadIcon />
          </Fab>
        </Box>
        <div
          className="addNodeContainer"
          style={{ display: "flex", background: "#fff" }}
        >
          <p className="tHeader">Adicionar</p>
          <hr />
          <div>
            <button className="react-terminal inicio" onClick={handleOpenModal}>
              <div className="inner inicio"></div>
              Entrada/Saída
            </button>
            <button
              className="react-process MixProof"
              onClick={handleOpenModal}
            >
              <div className="inner MixProof"></div>
              MixProof
            </button>
            <button className="react-decision Tanque" onClick={handleOpenModal}>
              <div className="inner Tanque"></div> Tank
            </button>
          </div>
        </div>
      </Panel>
      <CustomModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSubmit={handleSubmitModal}
        nodeType={nodeType}
      />
      {/* <LoadingModal open={loading} />
      <CreatedNodesModal open={successNode} />
      <CreatedEdgesModal open={successEdge} /> */}
    </>
  );
};
