import React, { useState } from 'react';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from '@mui/material';

interface ModalProps {
  open: boolean;
  onClose: () => void;
  onSubmit: (name: string, rpn: string, volume: string, condicao: string) => void;
  nodeType: string;
}

const CustomModal: React.FC<ModalProps> = ({ open, onClose, onSubmit, nodeType }) => {
  const [name, setName] = useState('');
  const [rpn, setRpn] = useState('');
  const [volume, setVolume] = useState('');
  const [condicao, setCondicao] = useState('');

  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleRpnChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRpn(event.target.value);
  };
  
  const handleVolumeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setVolume(event.target.value);
  };
  
  const handleCondicaoChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCondicao(event.target.value);
  };
  const handleSubmit = () => {
    onSubmit(name, rpn, volume, condicao);
    // Fechar o modal após o envio do formulário
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Criar nó {nodeType} </DialogTitle>
      <DialogContent>
        <DialogContentText>Preencha o formulário abaixo:</DialogContentText>
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Nome"
          type="text"
          fullWidth
          value={name}
          onChange={handleNameChange}
        />
        <TextField
          margin="dense"
          id="rpn"
          label="RPN"
          type="text"
          fullWidth
          value={rpn}
          onChange={handleRpnChange}
        />
        <TextField
          margin="dense"
          id="volume"
          label="Volume"
          type="text"
          fullWidth
          value={volume}
          onChange={handleVolumeChange}
        />
        <TextField
          margin="dense"
          id="address"
          label="Condição"
          type="text"
          fullWidth
          value={condicao}
          onChange={handleCondicaoChange}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancelar
        </Button>
        <Button onClick={handleSubmit} color="primary">
          Adicionar
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default CustomModal;