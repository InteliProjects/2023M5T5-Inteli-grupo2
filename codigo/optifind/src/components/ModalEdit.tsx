import React, { useState, useEffect } from 'react';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from '@mui/material';
import { updateNode, deleteNode } from '../services/nodes';
import { useCookies } from 'react-cookie';

interface ModalProps {
  open: boolean;
  onClose: () => void;
  nodeId: string;
  entireNode: any;
}

const ModalEdit: React.FC<ModalProps> = ({ open, onClose, nodeId, entireNode }) => {

  const [cookies] = useCookies(['authToken']);

  const [name, setName] = useState(" ");
  const [rpn, setRpn] = useState(" ");
  const [nodeType, setNodeType] = useState(" ");
  const [volume, setVolume] = useState(" ");
  const [condicao, setCondicao] = useState(" ");
  const [teste, setTeste] = useState(true);

  const loadValues = () => {
    setName(entireNode.data.label);
    setRpn(entireNode.data.rpn);
    setVolume(entireNode.data.volume);
    setCondicao(entireNode.data.condicao);
    setNodeType(entireNode.data.type);
  }

  useEffect(() => {
    if (open && teste) {
      loadValues();
      setTeste(false);
    }
  })

  const handleUpdateNode = async () => {
    const response = await updateNode(
      nodeId,
      name,
      nodeType,
      rpn,
      volume,
      condicao
    );
    console.log(response);
    
    onClose();
    setTeste(true);
  };

  const handleDeleteNode = async () => {
    await deleteNode(
      nodeId,
      name,
      rpn);

    onClose();
    setTeste(true);
  }

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Id do nó {nodeId}</DialogTitle>
      <DialogContent>
        <DialogContentText>Atualizar informações do nó:</DialogContentText>
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Nome"
          type="text"
          fullWidth
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <TextField
          margin="dense"
          id="rpn"
          label="RPN"
          type="text"
          fullWidth
          value={rpn}
          onChange={(e) => setRpn(e.target.value)}
        />            
        <TextField
          margin="dense"
          id="nodeType"
          label="Tipo de nó"
          type="text"
          fullWidth
          value={nodeType}
          onChange={(e) => setNodeType(e.target.value)}
        />        
        <TextField
          margin="dense"
          id="volume"
          label="Volume"
          type="text"
          fullWidth
          value={volume}
          onChange={(e) => setVolume(e.target.value)}
        />        
        <TextField
          margin="dense"
          id="condicao"
          label="Condição"
          type="text"
          fullWidth
          value={condicao}
          onChange={(e) => setCondicao(e.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleDeleteNode} color="warning">
          Deletar
        </Button>
        <Button onClick={handleUpdateNode} color="primary">
          Confirmar
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ModalEdit;