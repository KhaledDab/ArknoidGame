package Listeners;

/**
 * The HitNotifier interface represents objects that can notify listeners about hit events.
 * It provides methods for adding and removing HitListeners.
 * @author : dabbahk1
 * ID : 214075343
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to be notified about hit events.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}
