package com.example.psk1.ui;

import com.example.psk1.entities.Album;
import com.example.psk1.services.AlbumService;
import com.example.psk1.services.OptimisticLockService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class OptimisticLockController implements Serializable {

    @Inject
    private AlbumService albumService;

    @Inject
    private OptimisticLockService optLockService;

    @Getter @Setter
    private Long selectedAlbumId;

    @Getter @Setter
    private Integer newNumberOfSongsValue;

    private Album staleAlbumReference;

    @Getter
    private List<Album> availableAlbums;

    @Getter
    private boolean step1Complete = false;

    @Getter
    private boolean step2Complete = false;

    @Getter
    private boolean step3Failed = false;

    public void loadAlbums() {
        availableAlbums = albumService.getAllAlbums();

        // Resetting
        step1Complete = false;
        step2Complete = false;
        step3Failed = false;
        staleAlbumReference = null;
    }

    public void step1GetReference() {
        if (selectedAlbumId != null) {
            staleAlbumReference = optLockService.getAlbumReference(selectedAlbumId);
            if (staleAlbumReference != null) {
                newNumberOfSongsValue = staleAlbumReference.getNumberOfSongs();
                step1Complete = true;

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Step 1 Complete",
                                "Got a reference to album: " + staleAlbumReference.getTitle() +
                                        " with version: " + staleAlbumReference.getVersion()));
            }
        }
    }

    public void step2SimulateOtherUser() {
        if (staleAlbumReference != null) {
            optLockService.updateAlbumAsAnotherUser(staleAlbumReference.getId());
            step2Complete = true;

            Album updatedAlbum = albumService.getAlbumById(staleAlbumReference.getId());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Step 2 Complete",
                            "Another user updated the album. New number of songs value: " + updatedAlbum.getNumberOfSongs() +
                                    ", new version: " + updatedAlbum.getVersion()));
        }
    }

    public void step3UpdateWithStaleReference() {
        if (staleAlbumReference != null && newNumberOfSongsValue != null) {
            boolean updateSucceeded = optLockService.updateAlbumWithStaleReference(staleAlbumReference, newNumberOfSongsValue);

            if (updateSucceeded) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Unexpected Result",
                                "No OptimisticLockException was thrown. This is unexpected!"));
            } else {
                // expected
                step3Failed = true;

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Step 3 Complete",
                                "OptimisticLockException thrown as expected! The stale reference with version " +
                                        staleAlbumReference.getVersion() + " could not be used."));

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Status",
                                "The current transaction has been marked for rollback. " +
                                        "Any other operations in this transaction will fail."));
            }
        }
    }

    public void step4UpdateWithFreshReference() {
        if (selectedAlbumId != null && newNumberOfSongsValue != null) {
            optLockService.updateAlbumWithFreshReference(selectedAlbumId, newNumberOfSongsValue);

            // Get fresh reference to show updated values
            Album updatedAlbum = albumService.getAlbumById(selectedAlbumId);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Step 4 Complete",
                            "Successfully updated album with fresh reference. " +
                                    "New number of songs value: " + updatedAlbum.getNumberOfSongs() +
                                    ", new version: " + updatedAlbum.getVersion()));

            // Reset to start over
            step1Complete = false;
            step2Complete = false;
            step3Failed = false;
            staleAlbumReference = null;
        }
    }

    public Album getSelectedAlbum() {
        if (selectedAlbumId != null) {
            return albumService.getAlbumById(selectedAlbumId);
        }
        return null;
    }
}
