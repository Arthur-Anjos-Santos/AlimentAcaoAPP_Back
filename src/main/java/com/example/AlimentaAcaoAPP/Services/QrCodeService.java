package com.example.AlimentaAcaoAPP.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import com.example.AlimentaAcaoAPP.Repository.PessoaRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;

@Service
public class QrCodeService {
    @Autowired
    private PessoaRepository repository;

    public String obterQrCode(Integer idUsuario, boolean ehBeneficiario) {
        Optional<Pessoa> optionalPessoa = repository.findById(idUsuario);
        String qrCodeBase64 = null;

        try {
            if(!ehBeneficiario){
                return null;
            }

            qrCodeBase64 = gerarQrCodeBase64(String.valueOf(ehBeneficiario));

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar QR code", e);
        }
        Pessoa pessoa = optionalPessoa.get();
        pessoa.setQrCodeBase64(qrCodeBase64);
        
        repository.save(pessoa);

        return qrCodeBase64;
    }


    private String gerarQrCodeBase64(String texto) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, 200, 200);

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                int grayValue = (bitMatrix.get(x, y) ? 0 : 1) * 255;
                int rgb = (grayValue << 16) | (grayValue << 8) | grayValue;
                image.setRGB(x, y, rgb);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        byte[] pngData = baos.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }
}
